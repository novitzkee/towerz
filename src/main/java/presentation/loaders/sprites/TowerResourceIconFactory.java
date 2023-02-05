package presentation.loaders.sprites;

import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.ImageToIconConverter;
import game.engine.loaders.TowerIconFactory;
import game.tower.TowerLevel;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;
import static presentation.config.Resources.*;

public class TowerResourceIconFactory implements TowerIconFactory {

    private static final Vector2i TOWER_SPRITE_SIZE = new Vector2i(1, 2).mul(TILE_DIMENSIONS_PX);

    private static final List<Rect2i> ICON_LOCATIONS = getIconLocations();

    private static final double ICON_SCALE_FACTOR = 0.9f;

    @Override
    public Map<TowerLevel, ImageIcon> getArrowTowerIcons() {
        return loadIcons(TOWER1_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, ImageIcon> getElectricTowerIcons() {
        return loadIcons(TOWER2_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, ImageIcon> getCandyTowerIcons() {
        return loadIcons(TOWER3_BASES_SPRITE_SHEET);
    }

    @Override
    public Map<TowerLevel, ImageIcon> getBastionTowerIcons() {
        return loadIcons(TOWER4_BASES_SPRITE_SHEET);
    }

    private Map<TowerLevel, ImageIcon> loadIcons(String filename) {
        final List<ImageIcon> icons = ICON_LOCATIONS.stream()
                .map(location -> ImageToIconConverter.createIcon(filename, location, ICON_SCALE_FACTOR))
                .toList();

        return Map.of(
                TowerLevel.WEAK, icons.get(0),
                TowerLevel.MEDIUM, icons.get(1),
                TowerLevel.STRONG, icons.get(2));
    }

    private static List<Rect2i> getIconLocations() {
        return IntStream.range(0, 3)
                .mapToObj(i -> new Vector2i(i * TOWER_SPRITE_SIZE.getX(), 0))
                .map(position -> new Rect2i(position, TOWER_SPRITE_SIZE))
                .toList();
    }
}
