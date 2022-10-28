package engine.graphics;

import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public class StaticAnimation implements Animation {

    private final Sprite sprite;
    private final Positionable positionSource;

    @Override
    public void draw(Graphics2D graphics) {
        sprite.asDrawable(positionSource.position()).draw(graphics);
    }

    @Override
    public void tick() { }
}
