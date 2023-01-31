package engine.graphics.sprites;

import engine.geometry.Vector2i;
import engine.graphics.Drawable;

public interface Sprite extends Drawable {
    Vector2i getSize();
}
