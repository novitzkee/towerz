package com.github.towerz.engine.geometry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Circle {

    private final Vector2i center;

    private final int radius;

    public boolean contains(Vector2i point) {
        final double distanceFromCenter = point.sub(center).getLength();
        return distanceFromCenter <= radius;
    }
}
