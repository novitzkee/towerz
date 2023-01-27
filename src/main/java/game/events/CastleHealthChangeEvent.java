package game.events;

import game.creature.Health;

public record CastleHealthChangeEvent(Health currentHealth) { }
