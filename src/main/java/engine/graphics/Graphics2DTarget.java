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
    public void drawImage(Image image, Vector2i position, DrawingPositioning drawingPositioning) {
        graphics2D.drawImage(image, getDrawingTransformation(position, drawingPositioning), null);
    }

    private AffineTransform getDrawingTransformation(Vector2i position, DrawingPositioning drawingPositioning) {
        return switch (drawingPositioning) {
            case ABSOLUTE -> Transformations.offset(position);
            case RELATIVE -> Transformations.offset(positionTranslation.apply(position));
        };
    }
}
