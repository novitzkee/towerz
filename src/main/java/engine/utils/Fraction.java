package engine.utils;

import lombok.Getter;

public final class Fraction {
    @Getter
    private final float value;

    public Fraction(float value) {
        if (value > 1 || 0 > value) {
            throw new IllegalArgumentException("Invalid value for fraction: " + value);
        }

        this.value = value;
    }

    public Fraction(int a, int b) {
        this((float) a / b);
    }

    public Fraction mul(float f) {
        return new Fraction(value * f);
    }
}
