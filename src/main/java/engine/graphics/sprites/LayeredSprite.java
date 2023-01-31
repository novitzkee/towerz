package engine.graphics.sprites;

import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class LayeredSprite implements Sprite {

    private final List<Sprite> layers;

    public LayeredSprite(Sprite... sprites) {
        this(Arrays.asList(sprites));
    }

    @Override
    public void draw(Vector2i position, DrawingTarget drawingTarget, DrawingPositioning drawingPositioning) {
        layers.forEach(layer -> layer.draw(position, drawingTarget, drawingPositioning));
    }

    @Override
    public Vector2i getSize() {
        final int x = layers.stream()
                .map(Sprite::getSize)
                .mapToInt(Vector2i::getX)
                .max()
                .orElse(0);

        final int y = layers.stream()
                .map(Sprite::getSize)
                .mapToInt(Vector2i::getY)
                .max()
                .orElse(0);

        return new Vector2i(x, y);
    }
}
