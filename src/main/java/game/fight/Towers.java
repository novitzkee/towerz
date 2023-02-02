package game.fight;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.interactions.targets.TowerBuildingInteractionTarget;
import game.tower.Tower;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


@RequiredArgsConstructor
public class Towers implements Paintable, TimeAware {

    private static final Vector2i TOWER_BASE_SIZE = new Vector2i(1, 1);

    private final GameGeometry gameGeometry;

    private final Map<Vector2i, Tower> towers = new TreeMap<>();

    @Getter
    private final TowerBuildingInteractionTarget interactionTarget = new BuildingInteractionTarget();

    private void tryBuildTower(Vector2i position) {
        final Rect2i targetBuildingRect = new Rect2i(position, TOWER_BASE_SIZE);
    }

    private boolean containsTower(Vector2i position) {
        //return towers.stream().anyMatch()
        return false;
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        towers.values().forEach(tower -> tower.draw(drawingTarget));
    }

    @Override
    public void tick() {

    }

    public class BuildingInteractionTarget implements TowerBuildingInteractionTarget {

        @Override
        public boolean place(Tower tower) {
            if (towers.containsKey(tower.getPosition())) {
                return false;
            } else {
                towers.put(tower.getPosition(), tower);
                return true;
            }
        }

        @Override
        public boolean canPlace(Vector2i position) {
            final Rect2i buildingRect = new Rect2i(position, TOWER_BASE_SIZE);
            return gameGeometry.canBuildOn(buildingRect) && !towers.containsKey(position);
        }

        @Override
        public Optional<Tower> get(Vector2i position) {
            return Optional.ofNullable(towers.get(position));
        }
    }
}
