package com.github.towerz.game.events.world;

import com.github.towerz.engine.geometry.Range;

public record ProjectileHitEvent(Range damageRange, int damage) { }
