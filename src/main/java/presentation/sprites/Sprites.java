package presentation.sprites;

import engine.geometry.Vector2i;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;

public class Sprites {

    private static Vector2i translateMapPositionToRealPosition(Vector2i mapPosition) {
        return mapPosition.map(position -> position * TILE_DIMENSIONS_PX);
    }
}
