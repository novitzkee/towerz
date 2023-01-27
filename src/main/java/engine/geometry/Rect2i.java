package engine.geometry;

import lombok.RequiredArgsConstructor;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Rect2i {

    private final Vector2i position;

    private final Vector2i size;

    public boolean contains(Vector2i point) {
        final Vector2i bottomRight = position.add(size);

        return point.getX() >= position.getX()
                && point.getY() >= position.getY()
                && point.getX() < bottomRight.getX()
                && point.getY() < bottomRight.getY();
    }

    public Stream<Vector2i> units() {
        return xRange()
                .flatMap(x -> generateVectorRange(x, yRange()))
                .map(rectVector -> rectVector.add(position));
    }

    private Stream<Vector2i> generateVectorRange(int x, Stream<Integer> ys) {
        return ys.map(y -> new Vector2i(x, y));
    }

    private Stream<Integer> xRange() {
        return IntStream.range(0, size.getX())
                .boxed();
    }

    private Stream<Integer> yRange() {
        return IntStream.range(0, size.getY())
                .boxed();
    }
}
