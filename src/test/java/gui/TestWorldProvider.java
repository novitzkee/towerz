package gui;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import game.castle.Castle;
import game.creature.Health;
import game.world.GameMap;
import game.world.Path;
import game.world.Tile;
import game.world.World;
import presentation.sprites.CastleSpriteFactory;
import presentation.sprites.MapSpriteFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static presentation.config.Dimensions.WORLD_SIZE;

public class TestWorldProvider {

    private static final MapSpriteFactory mapSpriteFactory = new MapSpriteFactory();

    private static final CastleSpriteFactory castleSpriteFactory = new CastleSpriteFactory();

    private static final GameMap gameMap = new GameMap(WORLD_SIZE, generateTiles());

    private static final Castle castle = new Castle(
            new Vector2i(12, 6),
            new Health(1000),
            castleSpriteFactory.getCastleSprites());

    private static final Path path = new Path(generatePath());

    public static final World world = new World(castle, path, gameMap);

    private static Tile[][] generateTiles() {
        final Rect2i rockRect = new Rect2i(new Vector2i(9, 6), new Vector2i(3, 3));
        final Tile[][] result = new Tile[WORLD_SIZE.getX()][WORLD_SIZE.getY()];

        for (int x = 0; x < WORLD_SIZE.getX(); x++) {
            for (int y = 0; y < WORLD_SIZE.getY(); y++) {
                final Vector2i currentPosition = new Vector2i(x, y);
                result[x][y] = rockRect.contains(currentPosition) ? createRockTile() : createGrassTile();
            }
        }

        return result;
    }

    private static Tile createRockTile() {
        return new Tile(mapSpriteFactory.getRockSprite(), false);
    }

    private static Tile createGrassTile() {
        return new Tile(mapSpriteFactory.getGrassSprite(), false);
    }

    private static Set<Vector2i> generatePath() {
        return IntStream.range(0, 11)
                .mapToObj(x -> new Vector2i(x, 7))
                .collect(Collectors.toSet());
    }
}
