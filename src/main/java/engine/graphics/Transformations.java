package engine.graphics;

import engine.geometry.Vector2i;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.AffineTransform;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Transformations {

    public static AffineTransform identity() {
        return new AffineTransform();
    }

    public static AffineTransform offset(Vector2i offset) {
        final AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToTranslation(offset.getX(), offset.getY());
        return affineTransform;
    }

    public static AffineTransform rotate(Image image, double radians) {
        return AffineTransform.getRotateInstance(
                radians,
                image.getHeight(null) / 2d,
                image.getWidth(null) / 2d);
    }

    public static AffineTransform flipX(Image image) {
        final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        return tx;
    }

    public static AffineTransform flipY(Image image) {
        final AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        return tx;
    }

    public static AffineTransform scale(Image image, double scale) {
        final AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
        tx.translate(0 , 0);
        return tx;
    }

    public static AffineTransform combine(AffineTransform first, AffineTransform second) {
        final AffineTransform affineTransform = new AffineTransform();
        affineTransform.concatenate(second);
        affineTransform.concatenate(first);
        return affineTransform;
    }
}
