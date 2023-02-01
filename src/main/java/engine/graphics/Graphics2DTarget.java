package engine.graphics;

import engine.geometry.Vector2i;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.Function;

@RequiredArgsConstructor
public class Graphics2DTarget implements DrawingTarget {

    private final Graphics2D graphics2D;

    private final Function<Vector2i, Vector2i> positionTranslation;

    @Override
    public void drawImage(Image image, Vector2i position, AffineTransform transform, DrawingPositioning positioning) {
        final AffineTransform finalTransform = getFinalTransform(position, transform, positioning);
        graphics2D.drawImage(image, finalTransform, null);
    }


    private AffineTransform getFinalTransform(Vector2i position, AffineTransform transform, DrawingPositioning positioning) {
        final AffineTransform offsetTransform = positioning == DrawingPositioning.RELATIVE ?
                Transformations.offset(positionTranslation.apply(position)) :
                Transformations.offset(position);

        return Transformations.combine(transform, offsetTransform);
    }
}
