package presentation.components.resources;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.ImageToIconConverter;
import lombok.Getter;

import javax.swing.*;

import static presentation.config.Resources.*;

@Getter
public class SymbolIcons {

    private static final Vector2i ICON_POSITION = new Vector2i(0, 0);

    private static final Vector2i SMALL_ICON_SIZE = new Vector2i(16, 16);

    private static final Vector2i BIG_ICON_SIZE = new Vector2i(32, 32);

    private static final Rect2i SMALL_ICON_LOCATION = new Rect2i(ICON_POSITION, SMALL_ICON_SIZE);

    private static final Rect2i BIG_ICON_LOCATION = new Rect2i(ICON_POSITION, BIG_ICON_SIZE);

    private final ImageIcon smallGoldIcon = ImageToIconConverter.createIcon(GOLD_SMALL, SMALL_ICON_LOCATION, 1d);

    private final ImageIcon bigGoldIcon = ImageToIconConverter.createIcon(GOLD_BIG, BIG_ICON_LOCATION, 1d);

    private final ImageIcon bigHeartIcon = ImageToIconConverter.createIcon(HEART, BIG_ICON_LOCATION, 1d);

    private final ImageIcon arrowIcon = ImageToIconConverter.createIcon(ARROW, BIG_ICON_LOCATION, 1d);
}
