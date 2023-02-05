package game.interactions;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import game.castle.Castle;
import game.creature.Creature;
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
            new EnemyArrivalEventListener(),
            new EnemyDeathEventListener(),
            new SoldierArrivalListener());

    public boolean canPurchase(int price) {
        return goldAmount >= price;
    }

    public void purchase(int price) {
        updateGoldAndNotify(-price);
    }

    public void sell(int price) {
        updateGoldAndNotify(price);
    }

    private void updateHealthAndNotify(int damage) {
        castle.damage(damage);
        final CastleHealthChangeEvent event = new CastleHealthChangeEvent(castle.getCurrentHealth());
        eventEmitter.emit(event);
    }

    private void handleEnemyDeath(Creature enemy) {
        final int goldToIncrease = enemy.getHealth().getMaxAmount() / 100;
        updateGoldAndNotify(goldToIncrease);
    }

    private void handleSoldierArrival(Creature soldier) {
        final int goldToIncrease = soldier.getHealth().getMaxAmount() / 100;
        updateGoldAndNotify(goldToIncrease);
    }

    private void updateGoldAndNotify(int goldDelta) {
        goldAmount = Math.max(0, goldAmount + goldDelta);
        final CastleGoldChangeEvent event = new CastleGoldChangeEvent(goldAmount);
        eventEmitter.emit(event);
    }

    private class EnemyArrivalEventListener implements EventListener<EnemyArrivalEvent> {

        @Override
        public void onEvent(EnemyArrivalEvent event) {
            updateHealthAndNotify(event.monster().getDamage());
        }

        @Override
        public Class<EnemyArrivalEvent> getEventClass() {
            return EnemyArrivalEvent.class;
        }
    }

    private class EnemyDeathEventListener implements EventListener<EnemyDeathEvent> {

        @Override
        public void onEvent(EnemyDeathEvent event) {
            handleEnemyDeath(event.enemy());
        }

        @Override
        public Class<EnemyDeathEvent> getEventClass() {
            return EnemyDeathEvent.class;
        }
    }

    private class SoldierArrivalListener implements EventListener<SoldierArrivalEvent> {

        @Override
        public void onEvent(SoldierArrivalEvent event) {
            handleSoldierArrival(event.soldier());
        }

        @Override
        public Class<SoldierArrivalEvent> getEventClass() {
            return SoldierArrivalEvent.class;
        }
    }
}
