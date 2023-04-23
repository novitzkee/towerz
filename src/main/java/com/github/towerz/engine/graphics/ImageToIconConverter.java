package com.github.towerz.engine.graphics;

import com.github.towerz.engine.geometry.Rect2i;
import com.github.towerz.engine.graphics.sprites.SpriteSheet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageToIconConverter {

    public static ImageIcon createIcon(String filename, Rect2i subImageLocation, double scaleFactor) {
        final BufferedImage wholeImage = loadImage(filename);

        final BufferedImage subImage = wholeImage.getSubimage(
                subImageLocation.getPosition().getX(), subImageLocation.getPosition().getY(),
                subImageLocation.getSize().getX(), subImageLocation.getSize().getY());

        final BufferedImage scaledImage = transformSize(subImage, scaleFactor);
        return new ImageIcon(scaledImage);
    }


    private static BufferedImage transformSize(BufferedImage bufferedImage, double scaleFactor) {
        final AffineTransform scalingTransform = Transformations.scale(bufferedImage, scaleFactor);
        final AffineTransformOp affineTransformOp = new AffineTransformOp(scalingTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return affineTransformOp.filter(bufferedImage, null);
    }

    @SneakyThrows
    private static BufferedImage loadImage(String filename) {
        final URL imageURL = Objects.requireNonNull(SpriteSheet.class.getClassLoader().getResource(filename));
        return ImageIO.read(imageURL);
    }
}
