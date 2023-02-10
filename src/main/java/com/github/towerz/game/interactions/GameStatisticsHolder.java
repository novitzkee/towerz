package com.github.towerz.game.interactions;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.events.EventListener;
import com.github.towerz.engine.events.Subscriber;
import com.github.towerz.game.castle.Castle;
import com.github.towerz.game.events.interaction.castle.CastleGoldChangeEvent;
import com.github.towerz.game.events.interaction.castle.CastleHealthChangeEvent;
import com.github.towerz.game.events.interaction.gameplay.GameOverEvent;
import com.github.towerz.game.events.world.EnemyArrivalEvent;
import com.github.towerz.game.events.world.EnemyDeathEvent;
import com.github.towerz.game.events.world.SoldierArrivalEvent;
import lombok.Getter;

import java.util.List;

public class GameStatisticsHolder implements Subscriber {

    private final EventEmitter eventEmitter;
    private final Castle castle;

    @Getter
    private int goldAmount;

    public GameStatisticsHolder(EventEmitter eventEmitter, Castle castle, int startingGoldAmount) {
        this.eventEmitter = eventEmitter;
        this.castle = castle;
        this.goldAmount = startingGoldAmount;
    }

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(this::handleEnemyArrival, EnemyArrivalEvent.class),
            EventListener.of(this::handleEnemyDeath, EnemyDeathEvent.class),
            EventListener.of(this::handleSoldierArrival, SoldierArrivalEvent.class));

    public boolean canPurchase(int price) {
        return goldAmount >= price;
    }

    public void purchase(int price) {
        updateGoldAndNotify(-price);
    }

    public void sell(int price) {
        updateGoldAndNotify(price);
    }


    private void handleEnemyArrival(EnemyArrivalEvent enemyArrivalEvent) {
        castle.damage(enemyArrivalEvent.monster().getDamage());
        final CastleHealthChangeEvent event = new CastleHealthChangeEvent(castle.getCurrentHealth());
        eventEmitter.emit(event);

        if (castle.getCurrentHealth() == 0) {
            emitGameOverEvent();
        }
    }

    private void emitGameOverEvent() {
        final GameOverEvent gameOverEvent = new GameOverEvent();
        eventEmitter.emit(gameOverEvent);
    }

    private void handleEnemyDeath(EnemyDeathEvent enemyDeathEvent) {
        final int goldToIncrease = enemyDeathEvent.enemy().getHealth().getMaxAmount() / 33;
        updateGoldAndNotify(goldToIncrease);
    }

    private void handleSoldierArrival(SoldierArrivalEvent soldierArrivalEvent) {
        final int goldToIncrease = soldierArrivalEvent.soldier().getHealth().getMaxAmount() / 33;
        updateGoldAndNotify(goldToIncrease);
    }

    private void updateGoldAndNotify(int goldDelta) {
        goldAmount = Math.max(0, goldAmount + goldDelta);
        final CastleGoldChangeEvent event = new CastleGoldChangeEvent(goldAmount);
        eventEmitter.emit(event);
    }
}
