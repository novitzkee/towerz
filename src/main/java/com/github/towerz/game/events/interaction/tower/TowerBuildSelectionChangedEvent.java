package com.github.towerz.game.events.interaction.tower;

import com.github.towerz.game.events.interaction.PricedSelection;

public record TowerBuildSelectionChangedEvent(PricedSelection<TowerType> currentSelection) { }
