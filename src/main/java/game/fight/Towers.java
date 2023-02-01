package game.fight;

import engine.events.EventListener;
import engine.events.Subscriber;
import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.events.GameMapClickEvent;
import game.tower.Tower;
import game.tower.TowerFactory;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class Towers implements Paintable, TimeAware, Subscriber {

    private static final Vector2i TOWER_BASE_SIZE = new Vector2i(1, 1);

    private static final Vector2i TOWER_POSITION_OFFSET = new Vector2i(0, -1);

    private final GameGeometry gameGeometry;

    private final TowerFactory towerFactory;

    private final List<Tower> towers = new ArrayList<>();

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(new TowerBuildRequestListener());

    private void tryBuildTower(Vector2i position) {
        final Rect2i targetBuildingRect = new Rect2i(position, TOWER_BASE_SIZE);
        if(gameGeometry.canBuildOn(targetBuildingRect)) {
            final Tower tower = towerFactory.createBastionTower(position.add(TOWER_POSITION_OFFSET));
            towers.add(tower);
        }
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        towers.forEach(tower -> tower.draw(drawingTarget));
    }

    @Override
    public void tick() {

    }

    private class TowerBuildRequestListener implements EventListener<GameMapClickEvent> {

        @Override
        public void onEvent(GameMapClickEvent event) {
            tryBuildTower(event.position());
        }

        @Override
        public Class<GameMapClickEvent> getEventClass() {
            return GameMapClickEvent.class;
        }
    }
}
