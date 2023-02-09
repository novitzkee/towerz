package com.github.towerz.game.events.interaction.tower;

import com.github.towerz.engine.traits.Upgradeable;

public record TowerUpgradeSelectionChangedEvent(Upgradeable currentUpgradeable) { }
