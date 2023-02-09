package com.github.towerz.game.creature;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.animations.Animation;
import com.github.towerz.game.events.world.EnemyArrivalEvent;
import com.github.towerz.game.events.world.EnemyDeathEvent;
import com.github.towerz.game.events.world.SoldierArrivalEvent;
import com.github.towerz.game.world.GameGeometry;
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

    @Getter
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
        creatureBehaviour.clear();
    }

    @Override
    public Vector2i getMapPositionInTicks(int ticks) {
        if(state == CreatureState.WALKING) {
            final int positionInTicks = creatureBehaviour.step(ticks);
            return gameGeometry.toMapPosition(positionInTicks);
        } else {
            return gameGeometry.toMapPosition(pathPosition);
        }
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

        void clear();
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
        public void clear() {
            if (isAtDestination()) {
                final SoldierArrivalEvent soldierArrivalEvent = new SoldierArrivalEvent(AbstractCreature.this);
                eventEmitter.emit(soldierArrivalEvent);
            }
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
        public void clear() {
            if (isAtDestination()) {
                final EnemyArrivalEvent enemyArrivalEvent = new EnemyArrivalEvent(AbstractCreature.this);
                eventEmitter.emit(enemyArrivalEvent);
            } else {
                final EnemyDeathEvent enemyDeathEvent = new EnemyDeathEvent(AbstractCreature.this);
                eventEmitter.emit(enemyDeathEvent);
            }
        }
    }
}
