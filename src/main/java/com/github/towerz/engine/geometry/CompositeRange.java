package com.github.towerz.engine.geometry;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompositeRange implements Range {

    private final List<Range> subRanges;

    @Override
    public boolean contains(int value) {
        return subRanges.stream()
                .anyMatch(subRange -> subRange.contains(value));
    }
}
