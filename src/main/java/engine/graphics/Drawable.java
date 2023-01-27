package engine.graphics;

import engine.geometry.Vector2i;

import java.awt.*;

public interface Drawable {
    void draw(Vector2i position, Graphics2D graphics);
}
