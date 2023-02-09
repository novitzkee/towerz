package com.github.towerz.engine.geometry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicRange implements Range {

    private final int start;

    private final int end;

    public boolean contains(int value) {
        return start <= value && value <= end;
    }
}
