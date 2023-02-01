package game.world;

import engine.geometry.*;
import engine.utils.Pair;

public interface GameGeometry {

    Vector2i toRealPosition(int pathPosition);

    Vector2i toMapPosition(int pathPosition);

    Direction getPathDirection(int pathPosition);

    Range getPathRange(Vector2i mapPosition);

    Range getPathRange(Circle circle);

    Pair<Integer> getAttackerPositions();

    Pair<Integer> getDefenderPositions();

    boolean canBuildOn(Rect2i rect);
}
