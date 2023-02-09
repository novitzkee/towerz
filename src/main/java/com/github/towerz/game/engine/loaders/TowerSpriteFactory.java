package com.github.towerz.game.engine.loaders;

import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.game.tower.TowerLevel;

import java.util.Map;

public interface TowerSpriteFactory {

    Map<TowerLevel, Sprite> getArrowTowerSprites();

    Map<TowerLevel, Sprite> getElectricTowerSprites();

    Map<TowerLevel, Sprite> getCandyTowerSprites();

    Map<TowerLevel, Sprite> getBastionTowerSprites();
}
