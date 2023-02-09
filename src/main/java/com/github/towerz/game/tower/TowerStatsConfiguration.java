package com.github.towerz.game.tower;

import com.github.towerz.game.tower.projectiles.ProjectileStats;
import com.github.towerz.game.tower.utils.TowerStats;

import java.util.Map;

public class TowerStatsConfiguration {

    public static final Map<TowerLevel, TowerStats> ARROW_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 50, 150, 100),
            TowerLevel.MEDIUM, new TowerStats(4, 40,  200, 125),
            TowerLevel.STRONG, new TowerStats(4, 30,  250, 150));

    public static final Map<TowerLevel, ProjectileStats> ARROW_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(150, 7),
            TowerLevel.MEDIUM, new ProjectileStats(200, 7),
            TowerLevel.STRONG, new ProjectileStats(250, 7));

    public static final Map<TowerLevel, TowerStats> ELECTRIC_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 50,200, 100),
            TowerLevel.MEDIUM, new TowerStats(5, 40, 250, 125),
            TowerLevel.STRONG, new TowerStats(6, 30, 300, 150));

    public static final Map<TowerLevel, ProjectileStats> ELECTRIC_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(200, 10),
            TowerLevel.MEDIUM, new ProjectileStats(250, 10),
            TowerLevel.STRONG, new ProjectileStats(300, 10));

    public static final Map<TowerLevel, TowerStats> CANDY_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 30, 300, 150),
            TowerLevel.MEDIUM, new TowerStats(5, 25, 350, 175),
            TowerLevel.STRONG, new TowerStats(6, 20, 400, 200));

    public static final Map<TowerLevel, ProjectileStats> CANDY_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(150, 6),
            TowerLevel.MEDIUM, new ProjectileStats(150, 6),
            TowerLevel.STRONG, new ProjectileStats(200, 6));

    public static final Map<TowerLevel, TowerStats> BASTION_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(5, 50, 400, 200),
            TowerLevel.MEDIUM, new TowerStats(6, 50, 500, 250),
            TowerLevel.STRONG, new TowerStats(7, 50, 600, 300));

    public static final Map<TowerLevel, ProjectileStats> BASTION_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(500, 5),
            TowerLevel.MEDIUM, new ProjectileStats(1000, 5),
            TowerLevel.STRONG, new ProjectileStats(1500, 5));
}
