package engine.graphics;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface DrawingTarget {

    void drawRect(Rect2i rect2i, Color color, DrawingPositioning drawingPositioning);

    void drawImage(Image image, Vector2i position, AffineTransform transform, DrawingPositioning positioning);
}
