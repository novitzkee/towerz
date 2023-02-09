package com.github.towerz.game.world;

import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.graphics.sprites.Sprite;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Tile implements Paintable {

    private final Vector2i position;

    private final Sprite sprite;

    @Getter
    private final boolean canPlaceTower;

    @Override
    public void draw(DrawingTarget drawingTarget) {
        sprite.draw(position, drawingTarget, DrawingPositioning.RELATIVE);
    }
}
