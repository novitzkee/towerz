package engine.geometry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Direction {
    UP      (new Vector2i(0, -1)),
    RIGHT   (new Vector2i(1, 0)),
    DOWN    (new Vector2i(0, 1)),
    LEFT    (new Vector2i(-1, 0));

    @Getter
    private final Vector2i vector;
}
