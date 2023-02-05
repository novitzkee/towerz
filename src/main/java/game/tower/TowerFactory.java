package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import game.engine.loaders.TowerIconFactory;
import game.engine.loaders.TowerSpriteFactory;
import game.fight.Creatures;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.util.Map;

@RequiredArgsConstructor
public class TowerFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final TowerIconFactory towerIconFactory;

    private final TowerSpriteFactory towerSpriteFactory;

    private final Creatures creatures;

    public Tower createArrowTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getArrowTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getArrowTowerIcons();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getArrowProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, creatures, icons, baseSprites, projectileSprites, position);
    }

    public Tower createElectricTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getElectricTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getElectricTowerIcons();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getElectricProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, creatures, icons, baseSprites, projectileSprites, position);
    }

    public Tower createCandyTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getCandyTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getCandyTowerIcons();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getCandyTowerSprites();
        return new Tower(eventEmitter, gameGeometry, creatures, icons, baseSprites, projectileSprites, position);
    }

    public Tower createBastionTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getBastionTowerSprites();
        final Map<TowerLevel, ImageIcon> icons = towerIconFactory.getBastionTowerIcons();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getBastionProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, creatures, icons, baseSprites, projectileSprites, position);
    }
}
