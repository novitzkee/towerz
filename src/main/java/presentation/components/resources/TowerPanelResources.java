package presentation.components.resources;

import engine.geometry.Vector2i;
import engine.graphics.Transformations;
import engine.graphics.sprites.SpriteSheet;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

import static presentation.config.Resources.*;

@Getter
public class TowerPanelResources {

    private static final Vector2i TOWER_IMAGE_SIZE = new Vector2i(64, 128);

    private static final double ICON_SCALE_FACTOR = 0.8f;

    private final ImageIcon arrowTowerIcon;

    private final ImageIcon electricTowerIcon;

    private final ImageIcon candyTowerIcon;

    private final ImageIcon bastionTowerIcon;

    public TowerPanelResources() {
        arrowTowerIcon = createIcon(TOWER1_BASES_SPRITE_SHEET);
        electricTowerIcon = createIcon(TOWER2_BASES_SPRITE_SHEET);
        candyTowerIcon = createIcon(TOWER3_BASES_SPRITE_SHEET);
        bastionTowerIcon = createIcon(TOWER4_BASES_SPRITE_SHEET);
    }

    private static ImageIcon createIcon(String filename) {
        final BufferedImage wholeImage = loadImage(filename);
        final BufferedImage subImage = wholeImage.getSubimage(0, 0, TOWER_IMAGE_SIZE.getX(), TOWER_IMAGE_SIZE.getY());
        final BufferedImage scaledImage = transformSize(subImage);
        return new ImageIcon(scaledImage);
    }

    private static BufferedImage transformSize(BufferedImage bufferedImage) {
        final AffineTransform scalingTransform = Transformations.scale(bufferedImage, ICON_SCALE_FACTOR);
        final AffineTransformOp affineTransformOp = new AffineTransformOp(scalingTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return affineTransformOp.filter(bufferedImage, null);
    }

    @SneakyThrows
    private static BufferedImage loadImage(String filename) {
        final URL imageURL = Objects.requireNonNull(SpriteSheet.class.getClassLoader().getResource(filename));
        return ImageIO.read(imageURL);
    }
}
