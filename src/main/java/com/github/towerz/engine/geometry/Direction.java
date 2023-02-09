package com.github.towerz.engine.geometry;

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

    public static Direction invert(Direction direction) {
        return switch (direction) {
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case DOWN -> UP;
            case LEFT -> RIGHT;
        };
    }
}
