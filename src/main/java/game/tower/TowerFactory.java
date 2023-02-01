package game.tower;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.sprites.Sprite;
import game.engine.loaders.TowerSpriteFactory;
import game.world.GameGeometry;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class TowerFactory {

    private final EventEmitter eventEmitter;

    private final GameGeometry gameGeometry;

    private final TowerSpriteFactory towerSpriteFactory;

    public Tower createArrowTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getArrowTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getArrowProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, baseSprites, projectileSprites, position);
    }

    public Tower createElectricTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getElectricTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getElectricProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, baseSprites, projectileSprites, position);
    }

    public Tower createCandyTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getCandyTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getCandyTowerSprites();
        return new Tower(eventEmitter, gameGeometry, baseSprites, projectileSprites, position);
    }

    public Tower createBastionTower(Vector2i position) {
        final Map<TowerLevel, Sprite> baseSprites = towerSpriteFactory.getBastionTowerSprites();
        final Map<TowerLevel, Sprite> projectileSprites = towerSpriteFactory.getBastionProjectileSprites();
        return new Tower(eventEmitter, gameGeometry, baseSprites, projectileSprites, position);
    }
}
