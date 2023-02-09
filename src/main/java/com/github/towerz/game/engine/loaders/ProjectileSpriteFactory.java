package com.github.towerz.game.engine.loaders;

import com.github.towerz.engine.graphics.sprites.Sprite;
import com.github.towerz.game.tower.TowerLevel;

import java.util.Map;

public interface ProjectileSpriteFactory {

    Map<TowerLevel, Sprite> getArrowProjectileSprites();

    Map<TowerLevel, Sprite> getElectricProjectileSprites();

    Map<TowerLevel, Sprite> getCandyTowerProjectileSprites();

    Map<TowerLevel, Sprite> getBastionProjectileSprites();
}
