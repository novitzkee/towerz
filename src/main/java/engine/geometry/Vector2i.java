package engine.geometry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Vector2i implements Comparable<Vector2i> {

    private final int x;
    private final int y;

    public Vector2i add(Vector2i v) {
        return new Vector2i(x + v.x, y + v.y);
    }

    public Vector2i sub(Vector2i v) {
        return new Vector2i(x - v.y, y - v.y);
    }

    public Vector2i mul(int a) {
        return new Vector2i(x * a, y * a);
    }

    public Vector2i div(int a) {
        return new Vector2i(x / a, y / a);
    }

    public Vector2i map(Function<Integer, Integer> f) {
        return new Vector2i(f.apply(x), f.apply(y));
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2f toVector2f() {
        return new Vector2f(x, y);
    }

    public Vector2i interpolate(Vector2i v, float f) {
        return this.toVector2f()
                .mul(1 - f)
                .add(v.toVector2f().mul(f))
                .round();
    }

    public static boolean areInLine(Vector2i a, Vector2i b, Vector2i c) {
        return (a.x == b.x && b.x == c.x) || (a.y == b.y && b.y == c.y);
    }

    @Override
    public int compareTo(Vector2i v) {
        if(this.equals(v)) {
            return 0;
        } else if(this.x <= v.x && this.y <= v.y) {
            return -1;
        } else {
            return 1;
        }
    }
}
