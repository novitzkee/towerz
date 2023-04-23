package com.github.towerz.presentation.components.resources;

import lombok.SneakyThrows;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

import static com.github.towerz.presentation.config.Resources.FONT_PATH;

public class FontProvider {

    private static Font FONT;

    public static Font get() {
        if (FONT == null) {
            FONT = loadFont();
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(FONT);
        }

        return FONT;
    }

    @SneakyThrows
    private static Font loadFont() {
        final InputStream fontStream = Objects.requireNonNull(FontProvider.class.getClassLoader().getResourceAsStream(FONT_PATH));
        return Font.createFont(Font.TRUETYPE_FONT, fontStream);
    }
}
