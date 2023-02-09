package com.github.towerz.engine.graphics;

import com.github.towerz.engine.geometry.Rect2i;
import com.github.towerz.engine.geometry.Vector2i;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.Function;

@RequiredArgsConstructor
public class Graphics2DTarget implements DrawingTarget {

    private final Graphics2D graphics2D;

    private final Function<Vector2i, Vector2i> positionTranslation;

    @Override
    public void drawRect(Rect2i rect2i, Color color, DrawingPositioning drawingPositioning) {
        final Vector2i drawingPosition = drawingPositioning == DrawingPositioning.RELATIVE ?
                positionTranslation.apply(rect2i.getPosition()) :
                rect2i.getPosition();

        graphics2D.setColor(color);

        graphics2D.fillRect(
                drawingPosition.getX(), drawingPosition.getY(),
                rect2i.getSize().getX(), rect2i.getSize().getY());

        graphics2D.setColor(null);
    }

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
