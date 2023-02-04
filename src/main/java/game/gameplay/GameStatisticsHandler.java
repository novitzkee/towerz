package game.gameplay;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import game.castle.Castle;
import game.events.world.CastleHealthChangeEvent;
import game.events.world.EnemyArrivalEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameStatisticsHandler implements Subscriber {

    private final EventEmitter eventEmitter;
    private final Castle castle;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(new EnemyArrivalEventListener());

    private void updateHealthAndNotify(int damage) {
        castle.damage(damage);
        final CastleHealthChangeEvent event = new CastleHealthChangeEvent(castle.getCurrentHealth());
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
}
