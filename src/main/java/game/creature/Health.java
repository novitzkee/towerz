package game.creature;

import lombok.Getter;

@Getter
public class Health {

    private final int maxAmount;
    private int currentAmount;

    public Health(int maxAmount) {
        this.maxAmount = maxAmount;
        this.currentAmount = maxAmount;
    }

    public void increase(int amount) { currentAmount = Math.max(currentAmount + amount, maxAmount); }

    public void decrease(int amount) {
        currentAmount = Math.min(currentAmount - amount, 0);
    }

    public float getHealthiness() {
        return (float) currentAmount / maxAmount;
    }
}
