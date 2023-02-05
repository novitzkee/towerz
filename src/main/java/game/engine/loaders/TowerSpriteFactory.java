package game.engine.loaders;

import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;

import java.util.Map;

public interface TowerSpriteFactory {

    Map<TowerLevel, Sprite> getArrowTowerSprites();

    Map<TowerLevel, Sprite> getArrowProjectileSprites();

    Map<TowerLevel, Sprite> getElectricTowerSprites();

    Map<TowerLevel, Sprite> getElectricProjectileSprites();

    Map<TowerLevel, Sprite> getCandyTowerSprites();

    Map<TowerLevel, Sprite> getCandyTowerProjectileSprites();

    Map<TowerLevel, Sprite> getBastionTowerSprites();

    Map<TowerLevel, Sprite> getBastionProjectileSprites();
}
