package engine.graphics;

import engine.geometry.Vector2i;

public interface Drawable {

    void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning);

}
