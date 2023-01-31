package game.world;

import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
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
