package com.github.towerz.engine.time;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Accessors(fluent = true)
public class Delay {

    private final long amount;
    private final TimeUnit timeUnit;

    public static Delay ofMilliseconds(long milliseconds) {
        return new Delay(milliseconds, TimeUnit.MILLISECONDS);
    }

    public static Delay ratePerSecond(long ratePerSecond) {
        return new Delay(1000 / ratePerSecond, TimeUnit.MILLISECONDS);
    }
}
