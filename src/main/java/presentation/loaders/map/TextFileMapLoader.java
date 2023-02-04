package presentation.loaders.map;

import engine.geometry.BasicRange;
import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import game.engine.loaders.MapLoader;
import game.world.GameMap;
import game.world.PathSegment;
import game.world.Tile;
import lombok.SneakyThrows;
import presentation.loaders.sprites.MapResourceSpriteFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

import static presentation.config.Dimensions.WORLD_SIZE;
import static presentation.config.Dimensions.WORLD_TO_REAL_POSITION_TRANSLATION;
import static presentation.config.Gameplay.CREATURE_STEPS_PER_TILE;
import static presentation.config.Resources.*;

public class TextFileMapLoader implements MapLoader {

    private final MapResourceSpriteFactory mapSpriteFactory;

    private final Map<Direction, Sprite> pathSprites;

    private final Map<Direction, Sprite> turnSprites;

    private final String mapFilePath;

    private final Vector2i worldSize;

    private final BasicRange mapXRange;

    private final BasicRange mapYRange;

    public TextFileMapLoader() {
        this(WORLD_SIZE, WORLD_MAP_PATH);
    }

    public TextFileMapLoader(Vector2i worldSize, String mapFilePath) {
        this.mapSpriteFactory = new MapResourceSpriteFactory();
        this.pathSprites = mapSpriteFactory.getStraightPathSprites();
        this.turnSprites = mapSpriteFactory.getPathTurnSprites();

        this.worldSize = worldSize;
        this.mapXRange = new BasicRange(0, worldSize.getX() - 1);
        this.mapYRange = new BasicRange(0, worldSize.getY() - 1);
        this.mapFilePath = mapFilePath;
    }

    @SneakyThrows
    private List<String> loadMapFile() {
        final URL fileURL = Objects.requireNonNull(TextFileMapLoader.class.getClassLoader().getResource(mapFilePath));
        final File file = new File(fileURL.toURI());
        return Files.readAllLines(file.toPath());
    }

    @Override
    public GameMap load() {
        final List<String> map = loadMapFile();
        final List<Vector2i> path = findPath(map);
        final List<PathSegment> pathSegments = generatePathSegments(path);
        final Tile[][] tiles = generateTiles(map);

        return new GameMap(
                WORLD_TO_REAL_POSITION_TRANSLATION,
                worldSize,
                CREATURE_STEPS_PER_TILE,
                tiles,
                path,
                pathSegments);
    }

    private List<Vector2i> findPath(List<String> map) {
        final List<Vector2i> result = new ArrayList<>();

        final Vector2i startingPosition = findPathStart(map);
        final Vector2i nextPosition = startingPosition.add(Direction.RIGHT.getVector());

        result.add(startingPosition);

        Optional<Vector2i> currentPositionOptional = Optional.of(nextPosition);
        do {
            final Vector2i current = currentPositionOptional.get();
            final Vector2i previous = result.get(result.size() - 1);
            result.add(current);
            currentPositionOptional = findNextPathPosition(previous, current, map);
        } while (currentPositionOptional.isPresent());

        return result;
    }

    private Vector2i findPathStart(List<String> map) {
        for (int y = 0; y < WORLD_SIZE.getX(); y++) {
            if (map.get(y).charAt(0) == PATH_CHAR) {
                return new Vector2i(0, y);
            }
        }

        throw new IllegalArgumentException("Could not locate path start");
    }

    private Optional<Vector2i> findNextPathPosition(Vector2i previous, Vector2i current, List<String> map) {
        final List<Vector2i> positionsToSearch = Arrays.stream(Direction.values())
                .map(Direction::getVector)
                .map(current::add)
                .filter(position -> mapXRange.contains(position.getX()) && mapYRange.contains(position.getY()))
                .filter(position -> !position.equals(previous))
                .toList();

        final Optional<Vector2i> castle = findChar(CASTLE_CHAR, positionsToSearch, map);
        final Optional<Vector2i> path = findChar(PATH_CHAR, positionsToSearch, map);

        if (castle.isEmpty() && path.isEmpty()) {
            throw new IllegalArgumentException("Unexpected path end found at: " + current);
        }

        return path;
    }

    private Optional<Vector2i> findChar(char character, List<Vector2i> positionsToSearch, List<String> map) {
        return positionsToSearch.stream()
                .filter(position -> map.get(position.getY()).charAt(position.getX()) == character)
                .findFirst();
    }

    private List<PathSegment> generatePathSegments(List<Vector2i> path) {
        final List<PathSegment> segments = new ArrayList<>(path.size());

        final Vector2i pathStart = path.get(0);
        segments.add(createStartingSegment(pathStart));

        for (int i = 0; i < path.size() - 2; i++) {
            final Vector2i previous = path.get(i);
            final Vector2i current = path.get(i + 1);
            final Vector2i next = path.get(i + 2);

            final PathSegment pathSegment = Vector2i.areInLine(previous, current, next) ?
                    PathSegment.straight(previous, current, pathSprites) :
                    PathSegment.turn(previous, current, next, turnSprites);

            segments.add(pathSegment);
        }

        final Vector2i pathEnd = path.get(path.size() - 1);
        segments.add(createEndSegment(pathEnd));
        return segments;
    }

    private PathSegment createStartingSegment(Vector2i startPosition) {
        final Vector2i from = startPosition.add(Direction.LEFT.getVector());
        return PathSegment.straight(from, startPosition, pathSprites);
    }

    private PathSegment createEndSegment(Vector2i endPosition) {
        final Vector2i from = endPosition.add(Direction.LEFT.getVector());
        return PathSegment.straight(from, endPosition, pathSprites);
    }

    private Tile[][] generateTiles(List<String> map) {
        final Tile[][] result = new Tile[WORLD_SIZE.getX()][WORLD_SIZE.getY()];

        for (int x = 0; x < WORLD_SIZE.getX(); x++) {
            for (int y = 0; y < WORLD_SIZE.getY(); y++) {
                final Vector2i currentPosition = new Vector2i(x, y);
                final char character = map.get(y).charAt(x);
                result[x][y] = createTile(currentPosition, character);
            }
        }

        return result;
    }

    private Tile createTile(Vector2i position, char character) {
        return switch (character) {
            case ROCK_CHAR -> new Tile(position, mapSpriteFactory.getRockSprite(), false);
            case CASTLE_CHAR -> new Tile(position, mapSpriteFactory.getGrassSprite(), false);
            default -> new Tile(position, mapSpriteFactory.getGrassSprite(), true);
        };
    }
}
