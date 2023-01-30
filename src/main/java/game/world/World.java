package game.world;

import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.castle.Castle;
import game.fight.Fight;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class World implements Paintable, TimeAware {

    private final Castle castle;

    private final Fight fight;

    private final GameMap gameMap;

    @Override
    public void draw(DrawingTarget drawingTarget) {
        gameMap.draw(drawingTarget);
        castle.draw(drawingTarget);
        fight.draw(drawingTarget);
    }

    @Override
    public void tick() {
        fight.tick();
    }
}
