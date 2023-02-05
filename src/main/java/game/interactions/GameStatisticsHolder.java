package game.interactions;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import game.castle.Castle;
import game.events.interaction.castle.CastleGoldChangeEvent;
import game.events.interaction.castle.CastleHealthChangeEvent;
import game.events.world.EnemyArrivalEvent;
import game.events.world.EnemyDeathEvent;
import game.events.world.SoldierArrivalEvent;
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
            EventListener.of(this::handleEnemyArrivalEvent, EnemyArrivalEvent.class),
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


    private void handleEnemyArrivalEvent(EnemyArrivalEvent enemyArrivalEvent) {
        castle.damage(enemyArrivalEvent.monster().getDamage());
        final CastleHealthChangeEvent event = new CastleHealthChangeEvent(castle.getCurrentHealth());
        eventEmitter.emit(event);
    }

    private void handleEnemyDeath(EnemyDeathEvent enemyDeathEvent) {
        final int goldToIncrease = enemyDeathEvent.enemy().getHealth().getMaxAmount() / 100;
        updateGoldAndNotify(goldToIncrease);
    }

    private void handleSoldierArrival(SoldierArrivalEvent soldierArrivalEvent) {
        final int goldToIncrease = soldierArrivalEvent.soldier().getHealth().getMaxAmount() / 100;
        updateGoldAndNotify(goldToIncrease);
    }

    private void updateGoldAndNotify(int goldDelta) {
        goldAmount = Math.max(0, goldAmount + goldDelta);
        final CastleGoldChangeEvent event = new CastleGoldChangeEvent(goldAmount);
        eventEmitter.emit(event);
    }
}
