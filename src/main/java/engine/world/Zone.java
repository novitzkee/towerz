package engine.world;

import engine.geometry.Vector2i;

import java.util.Optional;
import java.util.stream.Stream;

public interface Zone {
    Optional<Tile> get(Vector2i position);
    Stream<Tile> tiles();
}
