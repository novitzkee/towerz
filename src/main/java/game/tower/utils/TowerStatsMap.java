package game.tower.utils;

import game.tower.TowerLevel;

public final class TowerStatsMap {
    private final TowerStats weakTowerStats;
    private final TowerStats mediumTowerStats;
    private final TowerStats strongTowerStats;

    public TowerStatsMap(TowerStats weakTowerStats, TowerStats mediumTowerStats, TowerStats strongTowerStats) {
        this.weakTowerStats = weakTowerStats;
        this.mediumTowerStats = mediumTowerStats;
        this.strongTowerStats = strongTowerStats;
    }

    public TowerStats getStatsForLevel(TowerLevel level) {
        return switch (level) {
            case WEAK -> weakTowerStats;
            case MEDIUM -> mediumTowerStats;
            case STRONG -> strongTowerStats;
        };
    }
}
