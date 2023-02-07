package game.engine.loaders;

import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;

import java.util.Map;

public interface ProjectileSpriteFactory {

    Map<TowerLevel, Sprite> getArrowProjectileSprites();

    Map<TowerLevel, Sprite> getElectricProjectileSprites();

    Map<TowerLevel, Sprite> getCandyTowerProjectileSprites();

    Map<TowerLevel, Sprite> getBastionProjectileSprites();
}
