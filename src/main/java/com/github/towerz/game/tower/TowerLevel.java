package com.github.towerz.game.tower;

public enum TowerLevel {
    WEAK,
    MEDIUM,
    STRONG;

    public static TowerLevel getNextLevel(TowerLevel currentLevel) {
        return switch (currentLevel) {
            case WEAK -> MEDIUM;
            case MEDIUM, STRONG -> STRONG;
        };
    }
}
