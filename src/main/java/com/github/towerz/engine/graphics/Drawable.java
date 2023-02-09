package com.github.towerz.engine.graphics;

import com.github.towerz.engine.geometry.Vector2i;

public interface Drawable {

    void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning);

}
