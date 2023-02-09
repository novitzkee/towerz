package com.github.towerz.engine.geometry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Vector2f {

    private final float x;
    private final float y;

    public Vector2f add(Vector2f v) {
        return new Vector2f(x + v.x, y + v.y);
    }

    public Vector2f sub(Vector2f v) {
        return new Vector2f(x - v.y, y - v.y);
    }

    public Vector2f mul(float a) {
        return new Vector2f(x * a, y * a);
    }

    public Vector2f map(Function<Float, Float> f) {
        return new Vector2f(f.apply(x), f.apply(y));
    }

    public Vector2i round() {
        return new Vector2i(Math.round(x), Math.round(y));
    }
}
