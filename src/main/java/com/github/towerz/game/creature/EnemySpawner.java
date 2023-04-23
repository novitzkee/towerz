package com.github.towerz.game.creature;

import com.github.towerz.engine.action.*;
import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.game.events.interaction.gameplay.GameWonEvent;
import com.github.towerz.game.events.interaction.gameplay.WaveElapsedEvent;
import com.github.towerz.game.fight.Creatures;

import java.util.ArrayList;
import java.util.List;

import static com.github.towerz.game.creature.WaveConfiguration.*;

public class EnemySpawner implements TimeAware {

    private final EventEmitter eventEmitter;

    private final MonsterFactory monsterFactory;

    private final Creatures creatures;

    private final Action spawningAction;

    public EnemySpawner(EventEmitter eventEmitter, MonsterFactory monsterFactory, Creatures creatures) {
        this.eventEmitter = eventEmitter;
        this.monsterFactory = monsterFactory;
        this.creatures = creatures;
        this.spawningAction = spawnerAction();
    }

    @Override
    public void tick() {
        spawningAction.tick();
    }

    private Action spawnerAction() {
        final List<Action> spawnActions = new ArrayList<>();

        for (int i = 1; i < TOTAL_WAVE_AMOUNT - 1; i++) {
            spawnActions.add(spawnNextWaveAndWaitForNoEnemyAction(i));
        }

        spawnActions.add(spawnFinalWaveAndWaitForNoEnemyAction());

        return new ConsecutiveAction(DELAY_BETWEEN_WAVES, spawnActions);
    }

    private Action spawnNextWaveAndWaitForNoEnemyAction(int waveNumber) {
        return new ConsecutiveAction(0, spawnWaveAction(waveNumber), waveElapsedAction(waveNumber));
    }

    private Action spawnFinalWaveAndWaitForNoEnemyAction() {
        return new ConsecutiveAction(0, spawnWaveAction(TOTAL_WAVE_AMOUNT), gameWonAction());
    }

    private Action spawnWaveAction(int waveNumber) {
        return new ParallelAction(
                spawnMantises(waveNumber),
                spawnBees(waveNumber),
                spawnButterflies(waveNumber),
                spawnBeetles(waveNumber));
    }

    private Action spawnMantises(int waveNumber) {
        final Runnable spawnMantis = () -> spawnMonster(monsterFactory.createMantis());
        return new RepeatedAction(delayBetweenCreaturesForWave(waveNumber), creatureCountForWave(waveNumber), spawnMantis);
    }

    private Action spawnBees(int waveNumber) {
        final Runnable spawnBee = () -> spawnMonster(monsterFactory.createBee());
        return new RepeatedAction(delayBetweenCreaturesForWave(waveNumber), creatureCountForWave(waveNumber), spawnBee);
    }

    private Action spawnButterflies(int waveNumber) {
        final Runnable spawnButterfly = () -> spawnMonster(monsterFactory.createButterfly());
        return new RepeatedAction(delayBetweenCreaturesForWave(waveNumber), creatureCountForWave(waveNumber), spawnButterfly);
    }

    private Action spawnBeetles(int waveNumber) {
        final Runnable spawnBeetle = () -> spawnMonster(monsterFactory.createBeetle());
        return new RepeatedAction(delayBetweenCreaturesForWave(waveNumber), creatureCountForWave(waveNumber), spawnBeetle);
    }

    private int creatureCountForWave(int waveNumber) {
        return (int) Math.round(CREATURES_PER_WAVE * Math.pow(WAVE_CREATURE_MULTIPLIER, waveNumber));
    }

    private int delayBetweenCreaturesForWave(int waveNumber) {
        return (int) Math.round(DELAY_BETWEEN_CREATURES * Math.pow(WAVE_DELAY_MULTIPLIER, waveNumber));
    }

    private void spawnMonster(Creature monster) {
        creatures.add(monster, CreatureType.ATTACKER);
    }

    private Action waveElapsedAction(int waveNumber) {
        final Runnable elapsedWaveAction = () -> notifyWaveElapsed(waveNumber);
        return new PredicateAction(elapsedWaveAction, () -> creatures.attackerCount() == 0);
    }

    private Action gameWonAction() {
        return new PredicateAction(this::notifyGameWon, () -> creatures.attackerCount() == 0);
    }

    private void notifyWaveElapsed(int waveNumber) {
        final WaveElapsedEvent event = new WaveElapsedEvent(waveNumber + 1, TOTAL_WAVE_AMOUNT);
        eventEmitter.emit(event);
    }

    private void notifyGameWon() {
        final GameWonEvent event = new GameWonEvent();
        eventEmitter.emit(event);
    }
}
