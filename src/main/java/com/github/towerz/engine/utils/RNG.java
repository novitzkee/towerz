package com.github.towerz.engine.utils;

import java.util.List;

public interface RNG {

    int randomInt(int origin, int bound);

    <T> T randomElement(List<T> list);
}
