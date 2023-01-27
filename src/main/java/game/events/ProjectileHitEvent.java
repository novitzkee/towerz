package game.events;

import engine.geometry.Vector2i;
import game.tower.Projectile;

public record ProjectileHitEvent(Projectile projectile, Vector2i position) { }
