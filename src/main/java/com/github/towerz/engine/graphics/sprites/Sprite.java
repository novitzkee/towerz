package com.github.towerz.engine.graphics.sprites;

import com.github.towerz.engine.geometry.Angle;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.Drawable;

import java.awt.geom.AffineTransform;

public interface Sprite extends Drawable {
    Vector2i getSize();

    Sprite flipX();

    Sprite flipY();

    Sprite rotate(Angle angle);

    Sprite apply(AffineTransform affineTransform);
}
