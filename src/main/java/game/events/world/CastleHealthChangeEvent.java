package game.events.world;

import game.creature.Health;

public record CastleHealthChangeEvent(Health currentHealth) { }
