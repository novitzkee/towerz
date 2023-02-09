package com.github.towerz.game.world;

import com.github.towerz.engine.graphics.DrawingTarget;
import com.github.towerz.engine.graphics.Paintable;
import com.github.towerz.engine.time.TimeAware;
import com.github.towerz.game.castle.Castle;
import com.github.towerz.game.fight.Fight;
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
