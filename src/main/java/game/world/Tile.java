package game.world;

import engine.geometry.Vector2i;
import engine.graphics.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

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
