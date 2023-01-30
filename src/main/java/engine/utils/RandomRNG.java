package engine.utils;

import java.util.List;
import java.util.Random;

public class RandomRNG implements RNG {

    private final Random random;

    public RandomRNG() {
        this.random = new Random();
    }

    public RandomRNG(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public int randomInt(int origin, int bound) {
        return random.nextInt(origin, bound);
    }

    @Override
    public <T> T randomElement(List<T> list) {
        final int randomIndex = random.nextInt(0, list.size());
        return list.get(randomIndex);
    }
}
