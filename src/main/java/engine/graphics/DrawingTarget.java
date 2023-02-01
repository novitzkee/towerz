package engine.graphics;

import engine.geometry.Vector2i;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface DrawingTarget {

    void drawImage(Image image, Vector2i position, AffineTransform transform, DrawingPositioning positioning);
}
