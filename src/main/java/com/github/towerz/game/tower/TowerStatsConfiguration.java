package com.github.towerz.game.tower;

import com.github.towerz.game.tower.projectiles.ProjectileStats;
import com.github.towerz.game.tower.utils.TowerStats;

import java.util.Map;

public class TowerStatsConfiguration {

    public static final Map<TowerLevel, TowerStats> ARROW_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 90, 300, 100),
            TowerLevel.MEDIUM, new TowerStats(4, 70,  400, 125),
            TowerLevel.STRONG, new TowerStats(4, 60,  500, 150));

    public static final Map<TowerLevel, ProjectileStats> ARROW_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(120, 7),
            TowerLevel.MEDIUM, new ProjectileStats(180, 7),
            TowerLevel.STRONG, new ProjectileStats(220, 7));

    public static final Map<TowerLevel, TowerStats> ELECTRIC_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 80,350, 100),
            TowerLevel.MEDIUM, new TowerStats(5, 50, 450, 125),
            TowerLevel.STRONG, new TowerStats(6, 40, 600, 150));

    public static final Map<TowerLevel, ProjectileStats> ELECTRIC_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(100, 10),
            TowerLevel.MEDIUM, new ProjectileStats(150, 10),
            TowerLevel.STRONG, new ProjectileStats(200, 10));

    public static final Map<TowerLevel, TowerStats> CANDY_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 50, 500, 150),
            TowerLevel.MEDIUM, new TowerStats(4, 40, 600, 175),
            TowerLevel.STRONG, new TowerStats(5, 30, 700, 200));

    public static final Map<TowerLevel, ProjectileStats> CANDY_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(120, 6),
            TowerLevel.MEDIUM, new ProjectileStats(150, 6),
            TowerLevel.STRONG, new ProjectileStats(180, 6));

    public static final Map<TowerLevel, TowerStats> BASTION_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(5, 80, 600, 200),
            TowerLevel.MEDIUM, new TowerStats(6, 80, 700, 250),
            TowerLevel.STRONG, new TowerStats(7, 80, 800, 300));

    public static final Map<TowerLevel, ProjectileStats> BASTION_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(500, 5),
            TowerLevel.MEDIUM, new ProjectileStats(800, 5),
            TowerLevel.STRONG, new ProjectileStats(1200, 5));
}
