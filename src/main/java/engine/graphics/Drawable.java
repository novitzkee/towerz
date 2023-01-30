package engine.graphics;

import engine.geometry.Vector2i;

import java.awt.*;

public interface Drawable {
    void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning);

    default void draw(Vector2i position, DrawingTarget drawingTarget) {
        draw(position, drawingTarget, DrawingPositioning.ABSOLUTE);
    }
}
