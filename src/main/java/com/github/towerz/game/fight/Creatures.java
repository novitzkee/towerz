package com.github.towerz.game.fight;

import com.github.towerz.engine.geometry.BasicRange;
import com.github.towerz.engine.geometry.Range;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.engine.utils.RNG;
import com.github.towerz.engine.utils.RandomRNG;
import com.github.towerz.game.creature.Creature;
import com.github.towerz.game.creature.CreatureState;
import com.github.towerz.game.creature.CreatureType;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Creatures implements TimeAware, Paintable {

    private static final int FIGHT_INIT_RANGE = 50;

    private static final int FIGHT_RANGE = 80;

    private final RNG rng = new RandomRNG();

    private final List<Creature> defendingCreatures = new ArrayList<>();

    private final List<Creature> attackingCreatures = new ArrayList<>();

    public Optional<Creature> getFirstMonsterInRange(Range range) {
        return attackingCreatures.stream()
                .filter(enemy -> range.contains(enemy.getPathPosition()))
                .max(Comparator.comparing(Creature::getPathPosition));
    }

    public List<Creature> getCreaturesInRange(Range range, CreatureType creatureType) {
        final List<Creature> creatures = switch (creatureType) {
            case ATTACKER -> attackingCreatures;
            case DEFENDER -> defendingCreatures;
        };

        return creatures.stream()
                .filter(creature -> range.contains(creature.getPathPosition()))
                .toList();
    }

    @Override
    public synchronized void tick() {
        setWalkingState();
        setFightingState();
        performFight();
        removeDeadCreatures();

        attackingCreatures.forEach(Creature::tick);
        defendingCreatures.forEach(Creature::tick);
    }

    @Override
    public synchronized void draw(DrawingTarget drawingTarget) {
        attackingCreatures.forEach(creature -> creature.draw(drawingTarget));
        defendingCreatures.forEach(creature -> creature.draw(drawingTarget));
    }

    private void setWalkingState() {
        attackingCreatures.forEach(attacker -> attacker.setState(CreatureState.WALKING));
        defendingCreatures.forEach(defender -> defender.setState(CreatureState.WALKING));
    }

    private void setFightingState() {
        final Optional<Creature> firstAttacker = getFirstCreature(CreatureType.ATTACKER);
        final Optional<Creature> firstDefender = getFirstCreature(CreatureType.DEFENDER);
        final Optional<Range> fightRange = getFightingRange(firstAttacker, firstDefender);

        fightRange.ifPresent(range ->
                setFightingStateForCreatures(getCreaturesInRange(range, CreatureType.ATTACKER)));

        fightRange.ifPresent(range ->
                setFightingStateForCreatures(getCreaturesInRange(range, CreatureType.DEFENDER)));
    }

    private Optional<Range> getFightingRange(Optional<Creature> attacker, Optional<Creature> defender) {
        if (attacker.isEmpty() || defender.isEmpty()) {
            return Optional.empty();
        }

        final Creature attackingCreature = attacker.get();
        final Creature defendingCreature = defender.get();

        return shouldInitFight(attackingCreature, defendingCreature) ?
                Optional.of(getFightPathRange(attackingCreature, defendingCreature)) :
                Optional.empty();
    }

    private boolean shouldInitFight(Creature attacker, Creature defender) {
        return Math.abs(attacker.getPathPosition() - defender.getPathPosition()) < FIGHT_INIT_RANGE;
    }

    private Range getFightPathRange(Creature attacker, Creature defender) {
        final int centerPoint = (attacker.getPathPosition() + defender.getPathPosition()) / 2;
        return new BasicRange(centerPoint - FIGHT_RANGE / 2, centerPoint + FIGHT_RANGE / 2);
    }

    private void setFightingStateForCreatures(List<Creature> targetCreatures) {
        targetCreatures.forEach(creature -> creature.setState(CreatureState.FIGHTING));
    }

    private Optional<Creature> getFirstCreature(CreatureType creatureType) {
        return switch (creatureType) {
            case ATTACKER -> attackingCreatures.stream()
                    .max(Comparator.comparing(Creature::getPathPosition));
            case DEFENDER -> defendingCreatures.stream()
                    .min(Comparator.comparing(Creature::getPathPosition));
        };
    }

    private void performFight() {
        final List<Creature> fightingDefenders = defendingCreatures.stream()
                .filter(creature -> creature.getState() == CreatureState.FIGHTING)
                .toList();

        final List<Creature> fightingAttackers = attackingCreatures.stream()
                .filter(creature -> creature.getState() == CreatureState.FIGHTING)
                .toList();

        performAttack(fightingDefenders, fightingAttackers);
        performAttack(fightingAttackers, fightingDefenders);
    }

    private void performAttack(List<Creature> attacking, List<Creature> defending) {
        attacking.forEach(attacker ->
                rng.randomElement(defending).takeDamage(attacker.getDamage()));
    }

    private void removeDeadCreatures() {
        attackingCreatures.stream()
                .filter(Creature::isGarbage)
                .forEach(Creature::cleanUp);

        attackingCreatures.removeIf(Creature::isGarbage);

        defendingCreatures.stream()
                .filter(Creature::isGarbage)
                .forEach(Creature::cleanUp);

        defendingCreatures.removeIf(Creature::isGarbage);
    }

    public synchronized void add(Creature creature, CreatureType creatureType) {
        switch (creatureType) {
            case ATTACKER -> attackingCreatures.add(creature);
            case DEFENDER -> defendingCreatures.add(creature);
        }
    }

    public int attackerCount() {
        return attackingCreatures.size();
    }
}
