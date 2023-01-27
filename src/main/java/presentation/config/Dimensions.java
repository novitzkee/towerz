package presentation.config;

import engine.geometry.Vector2i;

import java.util.function.Function;

public class Dimensions {

    public static final int JFRAME_X_BOUNDS = 14;

    public static final int JFRMAE_Y_BOUNDS = 35;

    public static final int SELECTION_WIDTH = 300;

    public static final int TILE_DIMENSIONS_PX = 64;

    public static final Vector2i TILE_SIZE_PX = new Vector2i(TILE_DIMENSIONS_PX, TILE_DIMENSIONS_PX);
    public static final Vector2i WORLD_SIZE = new Vector2i(15, 15);

    public static final Vector2i WORLD_SIZE_PX = WORLD_SIZE.map(size -> size * TILE_DIMENSIONS_PX);

    public static final Function<Vector2i, Vector2i> WORLD_TO_REAL_POSITION_TRANSLATION = Dimensions::translateMapPositionToRealPosition;

    private static Vector2i translateMapPositionToRealPosition(Vector2i worldPosition) {
        return worldPosition.map(position -> position * TILE_DIMENSIONS_PX);
    }
}
