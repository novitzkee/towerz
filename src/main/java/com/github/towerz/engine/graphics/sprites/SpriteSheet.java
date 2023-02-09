package com.github.towerz.engine.graphics.sprites;

import com.github.towerz.engine.geometry.Vector2i;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpriteSheet {

    private final BufferedImage spriteSheetImage;
    private final Vector2i spriteSize;

    public SpriteSheet(String imageFilename, Vector2i spriteSize) {
        this.spriteSheetImage = loadImage(imageFilename);
        this.spriteSize = spriteSize;
    }

    @SneakyThrows
    private static BufferedImage loadImage(String filename) {
        final URL imageURL = Objects.requireNonNull(SpriteSheet.class.getClassLoader().getResource(filename));
        return ImageIO.read(imageURL);
    }

    public BasicSprite getSprite(int x, int y) {
        final BufferedImage sprite = getSubImage(x, y);
        return new BasicSprite(sprite, spriteSize);
    }

    public List<Sprite> getSpriteRow(int y, int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> getSubImage(i, y))
                .map(image -> new BasicSprite(image, spriteSize))
                .collect(Collectors.toList());
    }

    private BufferedImage getSubImage(int x, int y) {
        return spriteSheetImage.getSubimage(
                spriteSize.getX() * x, spriteSize.getY() * y,
                spriteSize.getX(), spriteSize.getY());
    }
}
