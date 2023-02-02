package game.creature;

import engine.events.EventEmitter;
import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.animations.Animation;
import game.events.world.EnemyArrivalEvent;
import game.events.world.SoldierArrivalEvent;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

public abstract class AbstractCreature implements Creature {

    private final EventEmitter eventEmitter;

    protected final GameGeometry gameGeometry;

    private final Map<CreatureState, Animation> animations;

    private final CreatureBehaviour creatureBehaviour;

    @Getter
    private int pathPosition;

    private final Health health;

    @Getter
    private final int damage;

    @Getter
    @Setter
    private CreatureState state = CreatureState.WALKING;

    protected AbstractCreature(EventEmitter eventEmitter,
                               GameGeometry gameGeometry,
                               Map<CreatureState, Animation> animation,
                               CreatureType creatureType,
                               Health health,
                               int speed,
                               int damage) {
        this.eventEmitter = eventEmitter;
        this.gameGeometry = gameGeometry;
        this.animations = animation;

        this.creatureBehaviour = creatureType == CreatureType.ATTACKER ?
                new AttackerBehaviour(speed) :
                new DefenderBehaviour(speed);

        this.pathPosition = creatureBehaviour.getStartingPosition();
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        final Vector2i realPosition = gameGeometry.toRealPosition(pathPosition);
        animations.get(state).setDirection(creatureBehaviour.getDirection());
        animations.get(state).draw(realPosition, drawingTarget, DrawingPositioning.ABSOLUTE);
    }

    @Override
    public void tick() {
        if (state == CreatureState.WALKING && !creatureBehaviour.isAtDestination()) {
            pathPosition = creatureBehaviour.step(1);
        }
    }

    @Override
    public boolean isGarbage() {
        return health.getCurrentAmount() == 0 || creatureBehaviour.isAtDestination();
    }

    @Override
    public void cleanUp() {
        if (creatureBehaviour.isAtDestination()) {
            creatureBehaviour.signalArrival();
        }
    }

    @Override
    public int getPathPositionInTicks(int ticks) {
        return creatureBehaviour.step(ticks);
    }

    @Override
    public void takeDamage(int amount) {
        health.decrease(amount);
    }


    private interface CreatureBehaviour {

        int getStartingPosition();

        Direction getDirection();

        int step(int times);

        boolean isAtDestination();

        void signalArrival();
    }

    @RequiredArgsConstructor
    private class DefenderBehaviour implements CreatureBehaviour {

        private final int speed;

        @Override
        public int getStartingPosition() {
            return gameGeometry.getDefenderPositions().first();
        }

        @Override
        public Direction getDirection() {
            return Direction.invert(gameGeometry.getPathDirection(pathPosition));
        }

        @Override
        public int step(int times) {
            return pathPosition - speed * times;
        }

        @Override
        public boolean isAtDestination() {
            return gameGeometry.getDefenderPositions().second() >= pathPosition;
        }

        @Override
        public void signalArrival() {
            final SoldierArrivalEvent soldierArrivalEvent = new SoldierArrivalEvent(AbstractCreature.this);
            eventEmitter.emit(soldierArrivalEvent);
        }
    }

    @RequiredArgsConstructor
    private class AttackerBehaviour implements CreatureBehaviour {

        private final int speed;

        @Override
        public int getStartingPosition() {
            return gameGeometry.getAttackerPositions().first();
        }

        @Override
        public Direction getDirection() {
            return gameGeometry.getPathDirection(pathPosition);
        }

        @Override
        public int step(int times) {
            return pathPosition + speed * times;
        }

        @Override
        public boolean isAtDestination() {
            return gameGeometry.getAttackerPositions().second() <= pathPosition;
        }

        @Override
        public void signalArrival() {
            final EnemyArrivalEvent enemyArrivalEvent = new EnemyArrivalEvent(AbstractCreature.this);
            eventEmitter.emit(enemyArrivalEvent);
        }
    }
}
