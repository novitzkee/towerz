package engine.world;

import engine.geometry.Vector2i;
import engine.graphics.Animation;

import java.util.Optional;
import java.util.stream.Stream;

public class World implements Zone {

    private final Vector2i size;
    private final Tile[][] tiles;

    @Override
    public Optional<Tile> get(Vector2i position) {
        return Optional.empty();
    }

    @Override
    public Stream<Tile> tiles() {
        return null;
    }

    public Animation animation() {
        return null;
    }
}
