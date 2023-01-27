package engine.graphics;

import engine.geometry.Vector2i;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class LayeredSprite implements Sprite {

    private final List<Sprite> layers;

    public LayeredSprite(Sprite... sprites) {
        this(Arrays.asList(sprites));
    }

    @Override
    public void draw(Vector2i position, Graphics2D graphics) {
        layers.forEach(layer -> layer.draw(position, graphics));
    }

    @Override
    public Vector2i getSize() {
        return layers.stream()
                .findFirst()
                .map(Sprite::getSize)
                .orElse(new Vector2i(0, 0));
    }
}
