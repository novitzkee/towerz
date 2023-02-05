package game.events.interaction;

public record TowerBuildSelectionChangedEvent(PricedSelection<TowerType> currentSelection) { }
