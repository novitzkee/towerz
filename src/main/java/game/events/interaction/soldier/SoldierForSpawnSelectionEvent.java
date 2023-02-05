package game.events.interaction.soldier;

import game.events.interaction.PricedSelection;

public record SoldierForSpawnSelectionEvent(PricedSelection<SoldierType> currentSelection) { }
