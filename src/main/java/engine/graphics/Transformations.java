package engine.graphics;

import engine.geometry.Vector2i;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.geom.AffineTransform;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Transformations {

    public static AffineTransform offset(Vector2i offset) {
        final AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToTranslation(offset.getX(), offset.getY());
        return affineTransform;
    }
}
