package com.github.towerz.engine.graphics.sprites;

import com.github.towerz.engine.geometry.Angle;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.DrawingPositioning;
import com.github.towerz.engine.graphics.DrawingTarget;
import lombok.RequiredArgsConstructor;

import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

    @Override
    public Sprite flipX() {
        return transformLayers(Sprite::flipX);
    }

    @Override
    public Sprite flipY() {
        return transformLayers(Sprite::flipY);
    }

    @Override
    public Sprite rotate(Angle angle) {
        return transformLayers(sprite -> sprite.rotate(angle));
    }

    @Override
    public Sprite apply(AffineTransform affineTransform) {
        return transformLayers(sprite -> sprite.apply(affineTransform));
    }

    private LayeredSprite transformLayers(Function<Sprite, Sprite> transform) {
        final List<Sprite> transformed = layers.stream()
                .map(transform)
                .toList();

        return new LayeredSprite(transformed);
    }
}
