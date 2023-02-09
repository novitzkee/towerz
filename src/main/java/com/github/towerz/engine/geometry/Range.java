package com.github.towerz.engine.geometry;

public interface Range {
    boolean contains(int value);

    static Range empty() {
        return i -> false;
    }
}
