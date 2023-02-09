package com.github.towerz.engine.graphics.animations;

import com.github.towerz.engine.geometry.Direction;
import com.github.towerz.engine.graphics.Drawable;
import com.github.towerz.engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public interface Animation extends Drawable {

    Map<Direction, List<Sprite>> getSprites();

    Direction getDirection();

    void setDirection(Direction direction);
}
