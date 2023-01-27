package game.world;

import engine.geometry.Vector2i;
import engine.graphics.Drawable;
import engine.graphics.Sprite;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class Tile implements Drawable {

    private final Sprite sprite;

    @Getter
    private final boolean canPlaceTower;

    @Override
    public void draw(Vector2i position, Graphics2D graphics) {
        sprite.draw(position, graphics);
    }
}
