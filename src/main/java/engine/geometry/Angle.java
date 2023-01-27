package engine.geometry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Angle {

    @Getter
    private final double valueRadians;

    public static Angle fromRadians(float value) {
        return new Angle(normalize(value));
    }

    public static Angle between(Vector2i begin, Vector2i end) {
        final Vector2i displacement = end.sub(begin);
        final double valueRadians = Math.atan2(displacement.getX(), displacement.getY());
        return new Angle(valueRadians);
    }

    private static double normalize(float value) {
        return value % (2 * Math.PI);
    }

    public Vector2f asVector() {
        return new Vector2f((float) Math.cos(valueRadians), (float) Math.sin(valueRadians));
    }
}
