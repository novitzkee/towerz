package com.github.towerz.presentation.components.resources;

import com.github.towerz.engine.geometry.Rect2i;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.ImageToIconConverter;
import com.github.towerz.presentation.config.Resources;
import lombok.Getter;

import javax.swing.*;

@Getter
public class SoldierIcons {

    private static final Vector2i SOLDIER_SUBIMAGE_POSITION = new Vector2i(0, 128);

    private static final Vector2i SOLDIER_SUBIMAGE_SIZE = new Vector2i(64, 64);

    private static final Rect2i SOLDIER_SUBIMAGE_LOCATION = new Rect2i(SOLDIER_SUBIMAGE_POSITION, SOLDIER_SUBIMAGE_SIZE);

    private static final double ICON_SCALE_FACTOR = 1.15f;

    private final ImageIcon lightSoldierIcon = ImageToIconConverter.createIcon(
            Resources.SOLDIER1_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon mediumSoldierIcon = ImageToIconConverter.createIcon(
            Resources.SOLDIER2_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon heavySoldierIcon = ImageToIconConverter.createIcon(
            Resources.SOLDIER3_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);

    private final ImageIcon skeletonSoldierIcon = ImageToIconConverter.createIcon(
            Resources.SOLDIER4_SPRITE_SHEET,
            SOLDIER_SUBIMAGE_LOCATION,
            ICON_SCALE_FACTOR);
}
