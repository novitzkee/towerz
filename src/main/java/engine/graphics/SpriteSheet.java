package engine.graphics;

import engine.geometry.Vector2i;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SpriteSheet {

    private final BufferedImage spriteSheet;
    private final Vector2i spriteSize;

    public SpriteSheet(String imageFilename, Vector2i spriteSize) {
        this.spriteSheet = loadImage(imageFilename);
        this.spriteSize = spriteSize;
    }

    @SneakyThrows
    private static BufferedImage loadImage(String filename) {
        final URL imageURL = Objects.requireNonNull(SpriteSheet.class.getClassLoader().getResource(filename));
        return ImageIO.read(imageURL);
    }

    public BasicSprite getSprite(int x, int y) {

        final BufferedImage sprite = spriteSheet.getSubimage(
                spriteSize.getX() * x, spriteSize.getY() * y,
                spriteSize.getX(), spriteSize.getY());

        return new BasicSprite(sprite, spriteSize);
    }

    public List<BasicSprite> getSpriteRow(int y) {
        return Collections.emptyList();
    }
}
