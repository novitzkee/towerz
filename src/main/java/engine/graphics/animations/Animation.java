package engine.graphics.animations;

import engine.geometry.Direction;
import engine.graphics.Drawable;
import engine.graphics.sprites.Sprite;

import java.util.List;
import java.util.Map;

public interface Animation extends Drawable {

    Map<Direction, List<Sprite>> getSprites();

    Direction getDirection();

    void setDirection(Direction direction);
}
