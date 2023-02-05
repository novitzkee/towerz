package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import game.engine.loaders.TowerIconFactory;
import game.engine.loaders.TowerSpriteFactory;
import game.fight.Creatures;
import game.tower.utils.Stats;
import game.tower.utils.TowerEngine;
import game.tower.utils.TowerSprites;
import game.tower.utils.TowerStats;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.Map;

@RequiredArgsConstructor
public class TowerFactory {

    private static final TowerStats ARROW_TOWER_STATS = new TowerStats(
            new Stats(2, 50, 100, 70, 150, 100),
            new Stats(3, 40, 120, 70, 200, 125),
            new Stats(5, 30, 150, 70, 250, 150));

    private static final TowerStats ELECTRIC_TOWER_STATS = new TowerStats(
            new Stats(2, 50, 100, 100,200, 100),
            new Stats(3, 40, 120, 100, 250, 125),
            new Stats(5, 30, 150, 100, 300, 150));

    private static final TowerStats CANDY_TOWER_STATS = new TowerStats(
            new Stats(2, 30, 100, 60, 300, 150),
            new Stats(4, 20,150, 60, 350, 175),
            new Stats(6, 10, 250, 60, 400, 200));

    private static final TowerStats BASTION_TOWER_STATS = new TowerStats(
            new Stats(3, 50, 500, 50, 400, 200),
            new Stats(5, 50, 1000, 50, 500, 250),
            new Stats(7, 50, 1500, 50, 600, 300));

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final TowerIconFactory towerIconFactory;

    private final TowerSpriteFactory towerSpriteFactory;

    private final Creatures creatures;

    public Tower createArrowTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getArrowTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getArrowProjectileSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getArrowTowerIcons();

        final TowerSprites sprites = new TowerSprites(baseSprites, projectileSprites, icons);
        final TowerEngine towerEngine = new TowerEngine(null);

        return new Tower(creatures, ARROW_TOWER_STATS, towerEngine, sprites, position);
    }

    public Tower createElectricTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getElectricTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getElectricProjectileSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getElectricTowerIcons();

        final TowerSprites sprites = new TowerSprites(baseSprites, projectileSprites, icons);
        final TowerEngine towerEngine = new TowerEngine(null);

        return new Tower(creatures, ELECTRIC_TOWER_STATS, towerEngine, sprites, position);
    }

    public Tower createCandyTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getCandyTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getCandyTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getCandyTowerIcons();

        final TowerSprites sprites = new TowerSprites(baseSprites, projectileSprites, icons);
        final TowerEngine towerEngine = new TowerEngine(null);

        return new Tower(creatures, CANDY_TOWER_STATS, towerEngine, sprites, position);
    }

    public Tower createBastionTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getBastionTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getBastionProjectileSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getBastionTowerIcons();

        final TowerSprites sprites = new TowerSprites(baseSprites, projectileSprites, icons);
        final TowerEngine towerEngine = new TowerEngine(null);

        return new Tower(creatures, BASTION_TOWER_STATS, towerEngine, sprites, position);
    }
}
