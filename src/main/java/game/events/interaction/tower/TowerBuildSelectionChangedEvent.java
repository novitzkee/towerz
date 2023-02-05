package game.events.interaction.tower;

import game.events.interaction.PricedSelection;

public record TowerBuildSelectionChangedEvent(PricedSelection<TowerType> currentSelection) { }
