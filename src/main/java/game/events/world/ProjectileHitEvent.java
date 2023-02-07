package game.events.world;

import engine.geometry.Range;

public record ProjectileHitEvent(Range damageRange, int damage) { }
