package game.engine.loaders;

import engine.graphics.sprites.Sprite;
import game.tower.TowerLevel;

import java.util.Map;

public interface TowerSpriteFactory {

    Map<TowerLevel, Sprite> getArrowTowerSprites();

    Map<TowerLevel, Sprite> getElectricTowerSprites();

    Map<TowerLevel, Sprite> getCandyTowerSprites();

    Map<TowerLevel, Sprite> getBastionTowerSprites();
}
