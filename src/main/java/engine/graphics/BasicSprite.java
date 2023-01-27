package engine.graphics;

import engine.geometry.Angle;
import engine.geometry.Vector2i;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

@RequiredArgsConstructor
public class BasicSprite implements Sprite {
    private final BufferedImage bufferedImage;

    @Getter
    private final Vector2i size;

    public void draw(Vector2i position, Graphics2D target) {
        target.drawImage(bufferedImage, Transformations.offset(position.mul(64)), null);
    }

    public BasicSprite rotate(Angle byAngle) {
        return null;
    }

}
