package game.world;

import engine.geometry.*;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.utils.Fraction;
import engine.utils.Pair;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class GameMap implements Paintable {

    private final Vector2i size;

    private final Tile[][] tiles;

    private final Set<Vector2i> path;

    private final List<PathSegment> pathSegments;

    @Getter
    private final GameGeometry gameGeometry;

    public GameMap(Function<Vector2i, Vector2i> relativeTranslation,
                   Vector2i size,
                   int stepsPerTile,
                   Tile[][] tiles,
                   List<Vector2i> path,
                   List<PathSegment> pathSegments) {
        this.size = size;
        this.tiles = tiles;
        this.path = new HashSet<>(path);
        this.pathSegments = pathSegments;
        this.gameGeometry = new Geometry(relativeTranslation, stepsPerTile);
    }

    @Override
    public void draw(DrawingTarget drawingTarget) {
        drawTiles(drawingTarget);
        pathSegments.forEach(pathSegment -> pathSegment.draw(drawingTarget));
    }

    private void drawTiles(DrawingTarget drawingTarget) {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                tiles[x][y].draw(drawingTarget);
            }
        }
    }

    @RequiredArgsConstructor
    private class Geometry implements GameGeometry {

        private final Function<Vector2i, Vector2i> relativeTranslation;
        private final int stepsPerTile;

        @Override
        public Vector2i toRealPosition(int pathPosition) {
            final int position = normalize(pathPosition);
            final Fraction tilePosition = new Fraction((position % stepsPerTile), stepsPerTile);
            return getPathSegment(position).getPosition(tilePosition, relativeTranslation);
        }

        @Override
        public Vector2i toMapPosition(int pathPosition) {
            final int position = normalize(pathPosition);
            return getPathSegment(position).getPosition();
        }

        @Override
        public Direction getPathDirection(int pathPosition) {
            final int position = normalize(pathPosition);
            return getPathSegment(position).getDirection();
        }

        private PathSegment getPathSegment(int pathPosition) {
            final int index = pathPosition / stepsPerTile;
            final int normalizedIndex = index < 0 ? 0 : Math.min(index, pathSegments.size() - 1);
            return pathSegments.get(normalizedIndex);
        }

        private int normalize(int pathPosition) {
            return pathPosition < 0 ? 0 : Math.min(pathPosition, getMaxPathPosition());
        }

        @Override
        public Range getPathRange(Vector2i mapPosition) {
            return null;
        }

        @Override
        public Range getPathRange(Circle circle) {
            return null;
        }

        @Override
        public Pair<Integer> getAttackerPositions() {
            return new Pair<>(0,  getMaxPathPosition());
        }

        @Override
        public Pair<Integer> getDefenderPositions() {
            return new Pair<>(getMaxPathPosition(), 0);
        }

        private int getMaxPathPosition() {
            return pathSegments.size() * stepsPerTile - 1;
        }

        @Override
        public boolean canBuildOn(Rect2i rect) {
            return rect.units().allMatch(this::canBuildOnTile);
        }

        private boolean canBuildOnTile(Vector2i position) {
            return tiles[position.getX()][position.getY()].isCanPlaceTower() && !path.contains(position);
        }
    }
}
