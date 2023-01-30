package engine.graphics;

import engine.geometry.Direction;

import java.util.List;
import java.util.Map;

public interface Animation extends Drawable {

    Map<Direction, List<Sprite>> getSprites();

    Direction getDirection();

    void setDirection(Direction direction);
}
