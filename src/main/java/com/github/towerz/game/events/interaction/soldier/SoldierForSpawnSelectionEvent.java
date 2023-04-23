package com.github.towerz.game.events.interaction.soldier;

import com.github.towerz.game.events.interaction.PricedSelection;

public record SoldierForSpawnSelectionEvent(PricedSelection<SoldierType> currentSelection) {
}
