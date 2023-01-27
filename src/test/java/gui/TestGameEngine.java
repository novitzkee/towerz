package gui;

import engine.events.EventEmitter;
import engine.events.EventRouter;
import engine.time.Loop;
import game.world.World;
import presentation.loaders.GameEngine;

public class TestGameEngine implements GameEngine {

    @Override
    public EventEmitter getEventEmitter() {
        return TestEngineProvider.eventEmitter;
    }

    @Override
    public EventRouter getEventRouter() {
        return TestEngineProvider.eventRouter;
    }

    @Override
    public Loop getRepaintLoop() {
        return TestEngineProvider.repaintLoop;
    }

    @Override
    public World getWorldObject() {
        return TestWorldProvider.world;
    }
}
