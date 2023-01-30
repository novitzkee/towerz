package presentation.loaders.map;

import engine.geometry.Vector2i;
import engine.graphics.Sprite;
import game.castle.Castle;
import game.castle.State;
import game.creature.Health;
import game.engine.loaders.CastleLoader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import presentation.loaders.sprites.CastleSpriteResourceFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static presentation.config.Dimensions.WORLD_SIZE;
import static presentation.config.Gameplay.STARTING_CASTLE_HEALTH;
import static presentation.config.Resources.CASTLE_CHAR;
import static presentation.config.Resources.WORLD_MAP_PATH;

@RequiredArgsConstructor
public class TextFileCastleLoader implements CastleLoader {

    private static final int CASTLE_X_LOCATION = WORLD_SIZE.getX() - 3;

    private final CastleSpriteResourceFactory castleSpriteFactory = new CastleSpriteResourceFactory();

    @SneakyThrows
    private List<String> loadMapFile() {
        final URL fileURL = Objects.requireNonNull(TextFileMapLoader.class.getClassLoader().getResource(WORLD_MAP_PATH));
        final File file = new File(fileURL.toURI());
        return Files.readAllLines(file.toPath());
    }

    @Override
    public Castle load() {
        final List<String> map = loadMapFile();
        final Vector2i castlePosition = findCastlePosition(map);
        final Health castleHealth = new Health(STARTING_CASTLE_HEALTH);
        final Map<State, Sprite> castleSprites = castleSpriteFactory.getCastleSprites();
        return new Castle(castlePosition, castleHealth, castleSprites);
    }

    private Vector2i findCastlePosition(List<String> map) {
        for (int y = 0; y < WORLD_SIZE.getY(); y++) {
            final char character = map.get(y).charAt(CASTLE_X_LOCATION);
            if(character == CASTLE_CHAR) {
                return new Vector2i(CASTLE_X_LOCATION, y);
            }
        }

        throw new IllegalArgumentException("Castle could not be found on map");
    }
}
