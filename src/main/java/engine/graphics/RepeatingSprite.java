package engine.graphics;

import engine.geometry.Vector2i;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

@RequiredArgsConstructor
public class RepeatingSprite implements Sprite {

    private final BufferedImage bufferedImage;

    private final List<Vector2i> repeatPositions;

    @Override
    public void draw(Vector2i position, Graphics2D graphics) {
        repeatPositions.forEach(repeatPosition -> drawAt(graphics, position, repeatPosition));
    }

    private void drawAt(Graphics2D graphics, Vector2i drawingPosition, Vector2i offsetPosition) {
        graphics.drawImage(bufferedImage, getOffsetTransformation(drawingPosition, offsetPosition), null);
    }

    private AffineTransform getOffsetTransformation(Vector2i drawingPosition, Vector2i offsetPosition) {
        return Transformations.offset(drawingPosition.add(offsetPosition));
    }

    @Override
    public Vector2i getSize() {
        return null;
    }
}
