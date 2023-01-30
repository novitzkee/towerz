package game.fight;

import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.creature.EnemySpawner;
import game.creature.SoldierSpawner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Fight implements Paintable, TimeAware {

    private final Creatures creatures;

    private final EnemySpawner enemySpawner;

    private final SoldierSpawner soldierSpawner;

    @Override
    public void draw(DrawingTarget drawingTarget) {
        creatures.draw(drawingTarget);
    }

    @Override
    public void tick() {
        creatures.tick();
        soldierSpawner.tick();
        enemySpawner.tick();
    }
}
