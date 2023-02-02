package game.interactions.targets;

import engine.geometry.Vector2i;
import game.tower.Tower;

import java.util.Optional;

public interface TowerBuildingInteractionTarget {

    boolean place(Tower tower);

    boolean canPlace(Vector2i position);

    Optional<Tower> get(Vector2i position);
}
