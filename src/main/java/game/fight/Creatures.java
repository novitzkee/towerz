package game.fight;

import engine.geometry.BasicRange;
import engine.geometry.Circle;
import engine.geometry.Range;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import engine.utils.RNG;
import engine.utils.RandomRNG;
import game.creature.Creature;
import game.creature.CreatureState;
import game.creature.CreatureType;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Creatures implements TimeAware, Paintable {

    private static final int FIGHT_INIT_RANGE = 50;

    private static final int FIGHT_RANGE = 80;

    private final GameGeometry gameGeometry;

    private final RNG rng = new RandomRNG();

    private final List<Creature> defendingCreatures = new ArrayList<>();

    private final List<Creature> attackingCreatures = new ArrayList<>();

    public Optional<Creature> getFirstCreatureInCircle(Circle circle, CreatureType creatureType) {
        final Range range = gameGeometry.getPathRange(circle);

        return switch (creatureType) {
            case ATTACKER -> attackingCreatures.stream()
                    .filter(enemy -> range.contains(enemy.getPathPosition()))
                    .max(Comparator.comparing(Creature::getPathPosition));

            case DEFENDER -> attackingCreatures.stream()
                    .filter(enemy -> range.contains(enemy.getPathPosition()))
                    .min(Comparator.comparing(Creature::getPathPosition));
        };
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
    public void tick() {
        setWalkingState();
        setFightingState();
        performFight();
        removeDeadCreatures();

        attackingCreatures.forEach(Creature::tick);
        defendingCreatures.forEach(Creature::tick);
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
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
        final List<Creature> attackersToRemove = attackingCreatures.stream()
                .filter(Creature::isGarbage)
                .peek(Creature::cleanUp)
                .toList();

        attackingCreatures.removeAll(attackersToRemove);

        final List<Creature> defendersToRemove = defendingCreatures.stream()
                .filter(Creature::isGarbage)
                .peek(Creature::cleanUp)
                .toList();

        defendingCreatures.removeAll(defendersToRemove);
    }

    public synchronized void add(Creature creature, CreatureType creatureType) {
        switch (creatureType) {
            case ATTACKER -> attackingCreatures.add(creature);
            case DEFENDER -> defendingCreatures.add(creature);
        }
    }
}
