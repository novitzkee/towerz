package presentation.components.resources;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import lombok.Getter;

import javax.swing.*;

import static presentation.config.Resources.*;

@Getter
public class TowerIcons {

    private static final Vector2i TOWER_SUBIMAGE_POSITION = new Vector2i(0, 0);

    private static final Vector2i TOWER_SUBIMAGE_SIZE = new Vector2i(64, 128);

    private static final Rect2i TOWER_SUBIMAGE_LOCATION = new Rect2i(TOWER_SUBIMAGE_POSITION, TOWER_SUBIMAGE_SIZE);

    private static final double ICON_SCALE_FACTOR = 0.7f;

    private final ImageIcon arrowTowerIcon = SpriteToIconConverter.createIcon(
            TOWER1_BASES_SPRITE_SHEET,
            TOWER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon electricTowerIcon = SpriteToIconConverter.createIcon(
            TOWER2_BASES_SPRITE_SHEET,
            TOWER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon candyTowerIcon = SpriteToIconConverter.createIcon(
            TOWER3_BASES_SPRITE_SHEET,
            TOWER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon bastionTowerIcon = SpriteToIconConverter.createIcon(
            TOWER4_BASES_SPRITE_SHEET,
            TOWER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);
}
