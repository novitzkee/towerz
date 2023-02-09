package com.github.towerz.game.events.interaction;

public record PricedSelection<T>(int price, T selection) {

    public static <T> PricedSelection<T> empty() {
        return new PricedSelection<>(0, null);
    }
}
