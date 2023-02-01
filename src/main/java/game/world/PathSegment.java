package game.world;

import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.graphics.sprites.Sprite;
import engine.utils.Fraction;
import engine.utils.Pair;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PathSegment implements Paintable {

    private static final Map<Pair<Direction>, Direction> TURN_SPRITE_DIRECTIONS = Map.of(
            new Pair<>(Direction.RIGHT, Direction.UP), Direction.UP,
            new Pair<>(Direction.UP, Direction.RIGHT), Direction.RIGHT,
            new Pair<>(Direction.RIGHT, Direction.DOWN), Direction.DOWN,
            new Pair<>(Direction.DOWN, Direction.RIGHT), Direction.LEFT,
            new Pair<>(Direction.DOWN, Direction.LEFT), Direction.UP,
            new Pair<>(Direction.LEFT, Direction.DOWN), Direction.RIGHT,
            new Pair<>(Direction.UP, Direction.LEFT), Direction.DOWN,
            new Pair<>(Direction.LEFT, Direction.UP), Direction.LEFT);

    private final Sprite sprite;

    @Getter
    private final Direction direction;

    private final Vector2i from;

    @Getter
    private final Vector2i position;

    public static PathSegment straight(Vector2i from, Vector2i to, Map<Direction, Sprite> sprites) {
        final Direction direction = determineDirection(from, to).orElseThrow();
        final Sprite sprite = sprites.get(direction);

        return new PathSegment(sprite, direction, from, to);
    }

    public static PathSegment turn(Vector2i previous, Vector2i current, Vector2i next, Map<Direction, Sprite> sprites) {
        final Direction enterDirection = determineDirection(previous, current).orElseThrow();
        final Direction leaveDirection = determineDirection(current, next).orElseThrow();
        final Sprite sprite = getTurnSprite(sprites, new Pair<>(enterDirection, leaveDirection));

        return new PathSegment(sprite, enterDirection, previous, current);
    }

    private static Sprite getTurnSprite(Map<Direction, Sprite> sprites, Pair<Direction> directions) {
        final Direction spriteDirection = TURN_SPRITE_DIRECTIONS.get(directions);
        return sprites.get(spriteDirection);
    }

    public static Optional<Direction> determineDirection(Vector2i from, Vector2i to) {
        if (to.getX() == from.getX() && to.getY() == from.getY()) {
            return Optional.empty();
        } else if (to.getY() == from.getY() && to.getX() > from.getX()) {
            return Optional.of(Direction.RIGHT);
        } else if (to.getY() == from.getY() && to.getX() < from.getX()) {
            return Optional.of(Direction.LEFT);
        } else if (to.getX() == from.getX() && to.getY() < from.getY()) {
            return Optional.of(Direction.UP);
        } else if (to.getX() == from.getX() && to.getY() > from.getY()) {
            return Optional.of(Direction.DOWN);
        } else {
            return Optional.empty();
        }
    }

    public Vector2i getPosition(Fraction tilePosition, Function<Vector2i, Vector2i> positionTranslation) {
        final Vector2i realFrom = positionTranslation.apply(from);
        final Vector2i realPosition = positionTranslation.apply(position);
        return realFrom.interpolate(realPosition, tilePosition.getValue());
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        sprite.draw(position, drawingTarget, DrawingPositioning.RELATIVE);
    }
}
