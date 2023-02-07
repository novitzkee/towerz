package game.world;

import engine.geometry.*;
import engine.utils.Pair;

import java.util.List;

public interface GameGeometry {

    Vector2i toRealPosition(Vector2i mapPosition);

    Vector2i toRealPosition(int pathPosition);

    Vector2i toMapPosition(int pathPosition);

    Direction getPathDirection(int pathPosition);

    Pair<Integer> getAttackerPositions();

    Pair<Integer> getDefenderPositions();

    boolean canBuildOn(Rect2i rect);

    Range toPathRange(List<Vector2i> pathPositionRange);

    List<Vector2i> getMapPositionsInRange(Circle circle);
}
