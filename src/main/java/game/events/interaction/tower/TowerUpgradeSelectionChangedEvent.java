package game.events.interaction.tower;

import engine.traits.Upgradeable;

public record TowerUpgradeSelectionChangedEvent(Upgradeable currentUpgradeable) { }
