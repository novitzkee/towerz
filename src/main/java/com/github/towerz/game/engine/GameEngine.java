package com.github.towerz.game.engine;

import com.github.towerz.engine.events.EventEmitter;
import com.github.towerz.engine.events.EventRouter;
import com.github.towerz.engine.time.Loop;
import com.github.towerz.game.interactions.MouseInteractionHandler;
import com.github.towerz.game.world.World;

public interface GameEngine {
    EventEmitter getEventEmitter();

    EventRouter getEventRouter();

    Loop getRepaintLoop();

    World getWorldObject();

    MouseInteractionHandler getMouseInteractionHandler();

    void start();

    void stop();
}
