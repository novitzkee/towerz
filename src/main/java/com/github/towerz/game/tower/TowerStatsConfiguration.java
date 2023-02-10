package com.github.towerz.game.tower;

import com.github.towerz.game.tower.projectiles.ProjectileStats;
import com.github.towerz.game.tower.utils.TowerStats;

import java.util.Map;

public class TowerStatsConfiguration {

    public static final Map<TowerLevel, TowerStats> ARROW_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 80, 300, 100),
            TowerLevel.MEDIUM, new TowerStats(4, 65,  400, 125),
            TowerLevel.STRONG, new TowerStats(4, 45,  500, 150));

    public static final Map<TowerLevel, ProjectileStats> ARROW_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(180, 21),
            TowerLevel.MEDIUM, new ProjectileStats(250, 21),
            TowerLevel.STRONG, new ProjectileStats(360, 21));

    public static final Map<TowerLevel, TowerStats> ELECTRIC_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 4,450, 100),
            TowerLevel.MEDIUM, new TowerStats(4, 3, 550, 125),
            TowerLevel.STRONG, new TowerStats(5, 2, 600, 150));

    public static final Map<TowerLevel, ProjectileStats> ELECTRIC_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(10, 20),
            TowerLevel.MEDIUM, new ProjectileStats(10, 25),
            TowerLevel.STRONG, new ProjectileStats(10, 30));

    public static final Map<TowerLevel, TowerStats> CANDY_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(5, 80, 500, 150),
            TowerLevel.MEDIUM, new TowerStats(5, 70, 600, 175),
            TowerLevel.STRONG, new TowerStats(6, 60, 700, 200));

    public static final Map<TowerLevel, ProjectileStats> CANDY_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(250, 18),
            TowerLevel.MEDIUM, new ProjectileStats(600, 18),
            TowerLevel.STRONG, new ProjectileStats(1200, 18));

    public static final Map<TowerLevel, TowerStats> BASTION_TOWER_STATS = Map.of(
            TowerLevel.WEAK, new TowerStats(4, 20, 2000, 1000),
            TowerLevel.MEDIUM, new TowerStats(4, 20, 3000, 2000),
            TowerLevel.STRONG, new TowerStats(5, 20, 4000, 3000));

    public static final Map<TowerLevel, ProjectileStats> BASTION_TOWER_PROJECTILE_STATS = Map.of(
            TowerLevel.WEAK, new ProjectileStats(150, 20),
            TowerLevel.MEDIUM, new ProjectileStats(300, 30),
            TowerLevel.STRONG, new ProjectileStats(600, 40));
}
