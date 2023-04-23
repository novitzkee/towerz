package com.github.towerz.game.tower;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.geometry.Vector2i;
import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.game.engine.loaders.ProjectileSpriteFactory;
import com.github.towerz.game.engine.loaders.TowerIconFactory;
import com.github.towerz.game.engine.loaders.TowerSpriteFactory;
import com.github.towerz.game.fight.Creatures;
import com.github.towerz.game.tower.projectiles.ProjectileFactory;
import com.github.towerz.game.tower.projectiles.ProjectileStats;
import com.github.towerz.game.tower.utils.TowerMechanics;
import com.github.towerz.game.world.GameGeometry;

import javax.swing.*;
import java.util.Map;

public class TowerFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final TowerIconFactory towerIconFactory;

    private final TowerSpriteFactory towerSpriteFactory;

    private final Creatures creatures;

    private final ProjectileFactory arrowTowerProjectileFactory;

    private final ProjectileFactory electricTowerProjectileFactory;

    private final ProjectileFactory candyTowerProjectileFactory;

    private final ProjectileFactory bastionTowerProjectileFactory;

    public TowerFactory(EventEmitter eventEmitter,
                        GameGeometry gameGeometry,
                        TowerIconFactory towerIconFactory,
                        TowerSpriteFactory towerSpriteFactory,
                        ProjectileSpriteFactory projectileSpriteFactory,
                        Creatures creatures) {
        this.eventEmitter = eventEmitter;
        this.gameGeometry = gameGeometry;
        this.towerIconFactory = towerIconFactory;
        this.towerSpriteFactory = towerSpriteFactory;
        this.creatures = creatures;

        this.arrowTowerProjectileFactory = createProjectileFactory(projectileSpriteFactory.getArrowProjectileSprites(), TowerStatsConfiguration.ARROW_TOWER_PROJECTILE_STATS);
        this.electricTowerProjectileFactory = createProjectileFactory(projectileSpriteFactory.getElectricProjectileSprites(), TowerStatsConfiguration.ELECTRIC_TOWER_PROJECTILE_STATS);
        this.candyTowerProjectileFactory = createProjectileFactory(projectileSpriteFactory.getCandyTowerProjectileSprites(), TowerStatsConfiguration.CANDY_TOWER_PROJECTILE_STATS);
        this.bastionTowerProjectileFactory = createProjectileFactory(projectileSpriteFactory.getBastionProjectileSprites(), TowerStatsConfiguration.BASTION_TOWER_PROJECTILE_STATS);
    }

    private ProjectileFactory createProjectileFactory(Map<TowerLevel, Sprite> sprites, Map<TowerLevel, ProjectileStats> stats) {
        return new ProjectileFactory(eventEmitter, gameGeometry, sprites, stats);
    }

    public Tower createArrowTower(Vector2i position) {
        final Map<TowerLevel, Sprite> sprites = towerSpriteFactory.getArrowTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getArrowTowerIcons();

        final TowerMechanics towerMechanics = new TowerMechanics(creatures, gameGeometry, arrowTowerProjectileFactory);

        return new Tower(TowerStatsConfiguration.ARROW_TOWER_STATS, sprites, icons, towerMechanics, position);
    }

    public Tower createElectricTower(Vector2i position) {
        final Map<TowerLevel, Sprite> sprites = towerSpriteFactory.getElectricTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getElectricTowerIcons();

        final TowerMechanics towerMechanics = new TowerMechanics(creatures, gameGeometry, electricTowerProjectileFactory);

        return new Tower(TowerStatsConfiguration.ELECTRIC_TOWER_STATS, sprites, icons, towerMechanics, position);
    }

    public Tower createCandyTower(Vector2i position) {
        final Map<TowerLevel, Sprite> sprites = towerSpriteFactory.getCandyTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getCandyTowerIcons();

        final TowerMechanics towerMechanics = new TowerMechanics(creatures, gameGeometry, candyTowerProjectileFactory);

        return new Tower(TowerStatsConfiguration.CANDY_TOWER_STATS, sprites, icons, towerMechanics, position);
    }

    public Tower createBastionTower(Vector2i position) {
        final Map<TowerLevel, Sprite> sprites = towerSpriteFactory.getBastionTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getBastionTowerIcons();

        final TowerMechanics towerMechanics = new TowerMechanics(creatures, gameGeometry, bastionTowerProjectileFactory);

        return new Tower(TowerStatsConfiguration.BASTION_TOWER_STATS, sprites, icons, towerMechanics, position);
    }
}
