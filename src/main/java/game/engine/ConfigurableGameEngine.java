package game.engine;

import engine.events.EventEmitter;
import engine.events.EventRouter;
import engine.events.SingleDispatchEventRouter;
import engine.time.*;
import engine.time.delegators.TickDelegator;
import game.castle.Castle;
import game.creature.EnemySpawner;
import game.creature.SoldierSpawner;
import game.fight.Creatures;
import game.fight.Fight;
import game.world.GameMap;
import game.world.World;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurableGameEngine implements GameEngine {

    private final Loop mainLoop;

    private final Loop tickLoop;

    @Getter
    private final EventEmitter eventEmitter;

    @Getter
    private final EventRouter eventRouter;

    @Getter
    private final Loop repaintLoop;

    @Getter
    private final World worldObject;


    public static GameEngine configure(EngineConfig engineConfig) {

        final SingleDispatchEventRouter eventHandler = new SingleDispatchEventRouter();

        final Castle castle = engineConfig.getCastleLoader().load();
        final GameMap map = engineConfig.getMapLoader().load();

        final Creatures creatures = new Creatures(map.getGameGeometry());
        final EnemySpawner enemySpawner = new EnemySpawner(eventHandler, map.getGameGeometry(),  creatures);
        final SoldierSpawner soldierSpawner = new SoldierSpawner(eventHandler, map.getGameGeometry(), creatures);
        final Fight fight = new Fight(creatures, enemySpawner, soldierSpawner);

        final World world = new World(castle, fight, map);

        final Loop mainLoop = new ExecutorServiceLoop(Delay.ratePerSecond(30));
        final TimeAwareLoop tickLoop = new TimeAwareLoop();
        final TimeAwareLoop repaintLoop = new TimeAwareLoop();
        final TimeAware delegatedRepaintLoop = new TickDelegator(SwingUtilities::invokeLater, repaintLoop);

        mainLoop.add(tickLoop);
        mainLoop.add(delegatedRepaintLoop);
        tickLoop.add(fight);

        return new ConfigurableGameEngine(mainLoop, tickLoop, eventHandler, eventHandler, repaintLoop, world);
    }

    @Override
    public void start() {
        tickLoop.start();
        repaintLoop.start();
        mainLoop.start();
    }

    @Override
    public void stop() {
        mainLoop.stop();
    }
}
