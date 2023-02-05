package presentation.components.resources;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.ImageToIconConverter;
import lombok.Getter;

import javax.swing.*;

import static presentation.config.Resources.*;

@Getter
public class SoldierIcons {

    private static final Vector2i SOLDIER_SUBIMAGE_POSITION = new Vector2i(0, 128);

    private static final Vector2i SOLDIER_SUBIMAGE_SIZE = new Vector2i(64, 64);

    private static final Rect2i SOLDIER_SUBIMAGE_LOCATION = new Rect2i(SOLDIER_SUBIMAGE_POSITION, SOLDIER_SUBIMAGE_SIZE);

    private static final double ICON_SCALE_FACTOR = 1.15f;

    private final ImageIcon lightSoldierIcon = ImageToIconConverter.createIcon(
            SOLDIER1_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon mediumSoldierIcon = ImageToIconConverter.createIcon(
            SOLDIER2_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon heavySoldierIcon = ImageToIconConverter.createIcon(
            SOLDIER3_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon skeletonSoldierIcon = ImageToIconConverter.createIcon(
            SOLDIER4_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);
}
