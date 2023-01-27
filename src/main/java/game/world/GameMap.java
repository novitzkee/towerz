package game.world;

import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class GameMap implements Paintable {

    private final Vector2i size;

    private final Tile[][] tiles;

    @Override
    public void draw(Graphics2D graphics) {
        for(int x = 0; x < size.getX(); x++) {
            for(int y = 0; y < size.getY(); y++) {
                tiles[x][y].draw(new Vector2i(x, y), graphics);
            }
        }
    }
}
