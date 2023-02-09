package com.github.towerz.engine.graphics.sprites;

import com.github.towerz.engine.geometry.Angle;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Transformations;
import lombok.Getter;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BasicSprite implements Sprite {

    private final BufferedImage bufferedImage;

    @Getter
    private final Vector2i size;

    private final AffineTransform affineTransform;

    public BasicSprite(BufferedImage bufferedImage, Vector2i size) {
        this.bufferedImage = bufferedImage;
        this.size = size;
        this.affineTransform = new AffineTransform();
    }

    private BasicSprite(BufferedImage bufferedImage, Vector2i size, AffineTransform affineTransform) {
        this.bufferedImage = bufferedImage;
        this.size = size;
        this.affineTransform = affineTransform;
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        drawingTarget.drawImage(bufferedImage, position, affineTransform, drawingPositioning);
    }

    public BasicSprite rotate(Angle byAngle) {
        final AffineTransform nextTransform = Transformations.rotate(bufferedImage, byAngle.getValueRadians());
        return new BasicSprite(bufferedImage, size,
                Transformations.combine(affineTransform, nextTransform));
    }

    @Override
    public Sprite flipX() {
        final AffineTransform nextTransform = Transformations.flipX(bufferedImage);
        return new BasicSprite(bufferedImage, size,
                Transformations.combine(affineTransform, nextTransform));
    }

    @Override
    public Sprite flipY() {
        final AffineTransform nextTransform = Transformations.flipY(bufferedImage);
        return new BasicSprite(bufferedImage, size,
                Transformations.combine(affineTransform, nextTransform));
    }

    @Override
    public Sprite apply(AffineTransform affineTransform) {
        return new BasicSprite(bufferedImage, size, Transformations.combine(this.affineTransform, affineTransform));
    }
}
