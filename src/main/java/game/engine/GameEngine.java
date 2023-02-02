package game.engine;

import engine.events.EventEmitter;
import engine.events.EventRouter;
import engine.time.Loop;
import game.interactions.GameMapMouseInteractionHandler;
import game.world.World;

public interface GameEngine {
    EventEmitter getEventEmitter();

    EventRouter getEventRouter();

    Loop getRepaintLoop();

    World getWorldObject();

    GameMapMouseInteractionHandler getGameMapMouseInteractionHandler();

    void start();

    void stop();
}
