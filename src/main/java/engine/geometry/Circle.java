package engine.geometry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Circle {

    private final Vector2i center;

    private final int radius;

    public boolean contains(Vector2i point) {
        final Vector2i distanceFromCenter = point.sub(center);
        return distanceFromCenter.getLength() <= radius;
    }
}
