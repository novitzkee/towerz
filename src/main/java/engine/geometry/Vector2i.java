package engine.geometry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Vector2i {

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

    public Vector2i map(Function<Integer, Integer> f) {
        return new Vector2i(f.apply(x), f.apply(y));
    }
}
