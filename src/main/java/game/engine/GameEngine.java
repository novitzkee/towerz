package game.engine;

import engine.events.EventEmitter;
import engine.events.EventRouter;
import engine.time.Loop;
import game.interactions.TowerMouseInteractionHandler;
import game.world.World;

public interface GameEngine {
    EventEmitter getEventEmitter();

    EventRouter getEventRouter();

    Loop getRepaintLoop();

    World getWorldObject();

    TowerMouseInteractionHandler getTowerMouseInteractionHandler();

    void start();

    void stop();
}
