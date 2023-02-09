package com.github.towerz.game.events.world;

import com.github.towerz.game.creature.Creature;

public record EnemyArrivalEvent(Creature monster) { }
