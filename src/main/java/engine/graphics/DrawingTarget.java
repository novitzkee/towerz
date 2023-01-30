package engine.graphics;

import engine.geometry.Vector2i;

import java.awt.*;

public interface DrawingTarget {
    void drawImage(Image image, Vector2i position, DrawingPositioning drawingPositioning);

    default void drawImage(Image image, Vector2i position) {
        drawImage(image, position, DrawingPositioning.ABSOLUTE);
    }
}
