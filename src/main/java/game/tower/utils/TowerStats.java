package game.tower.utils;

import game.tower.TowerLevel;

public final class TowerStats {
    private final Stats weakStats;
    private final Stats mediumStats;
    private final Stats strongStats;

    public TowerStats(Stats weakStats, Stats mediumStats, Stats strongStats) {
        this.weakStats = weakStats;
        this.mediumStats = mediumStats;
        this.strongStats = strongStats;
    }

    public Stats getStatsForLevel(TowerLevel level) {
        return switch (level) {
            case WEAK -> weakStats;
            case MEDIUM -> mediumStats;
            case STRONG -> strongStats;
        };
    }
}
