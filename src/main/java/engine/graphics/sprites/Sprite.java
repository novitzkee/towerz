package engine.graphics.sprites;

import engine.geometry.Angle;
import engine.geometry.Vector2i;
import engine.graphics.Drawable;

import java.awt.geom.AffineTransform;

public interface Sprite extends Drawable {
    Vector2i getSize();

    Sprite flipX();

    Sprite flipY();

    Sprite rotate(Angle angle);

    Sprite apply(AffineTransform affineTransform);
}
