package com.github.towerz.game.interactions.targets;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.game.tower.Tower;

import java.util.Optional;

public interface TowerBuildingInteractionTarget {

    boolean remove(Vector2i position);

    boolean place(Tower tower);

    boolean canPlace(Vector2i position);

    Optional<Tower> get(Vector2i position);
}
