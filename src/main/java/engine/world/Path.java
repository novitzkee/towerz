package engine.world;

import engine.geometry.Vector2i;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Path implements Zone {

    private final Map<Vector2i, Tile> positionedTiles;

    public Optional<Tile> get(Vector2i position) {
        return Optional.of(positionedTiles.get(position));
    }

    @Override
    public Stream<Tile> tiles() {
        return positionedTiles.values().stream();
    }
}
