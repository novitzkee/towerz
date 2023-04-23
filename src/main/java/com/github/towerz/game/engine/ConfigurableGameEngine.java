package com.github.towerz.game.engine;

import com.github.towerz.engine.events.*;
import com.github.towerz.engine.time.*;
import com.github.towerz.engine.time.delegators.TickDelegator;
import com.github.towerz.game.castle.Castle;
import com.github.towerz.game.creature.EnemySpawner;
import com.github.towerz.game.creature.MonsterFactory;
import com.github.towerz.game.creature.SoldierFactory;
import com.github.towerz.game.creature.SoldierSpawner;
import com.github.towerz.game.engine.loaders.*;
import com.github.towerz.game.events.interaction.gameplay.GameOverEvent;
import com.github.towerz.game.events.interaction.gameplay.GameWonEvent;
import com.github.towerz.game.fight.Creatures;
import com.github.towerz.game.fight.Fight;
import com.github.towerz.game.fight.Towers;
import com.github.towerz.game.interactions.GameStatisticsHolder;
import com.github.towerz.game.interactions.MouseInteractionHandler;
import com.github.towerz.game.interactions.SoldierSpawnInteractionHandler;
import com.github.towerz.game.tower.TowerFactory;
import com.github.towerz.game.world.GameMap;
import com.github.towerz.game.world.World;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

import static com.github.towerz.presentation.config.Gameplay.STARTING_GOLD_AMOUNT;

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

    @Getter
    private final MouseInteractionHandler mouseInteractionHandler;


    public static GameEngine configure(EngineConfig engineConfig) {
        final MonsterAnimationFactory monsterAnimationFactory = engineConfig.getMonsterAnimationFactory();
        final SoldierAnimationFactory soldierAnimationFactory = engineConfig.getSoldierAnimationFactory();
        final TowerIconFactory towerIconFactory = engineConfig.getTowerIconFactory();
        final TowerSpriteFactory towerSpriteFactory = engineConfig.getTowerSpriteFactory();
        final ProjectileSpriteFactory projectileSpriteFactory = engineConfig.getProjectileSpriteFactory();

        final SingleDispatchEventRouter eventHandler = new SingleDispatchEventRouter();

        final Castle castle = engineConfig.getCastleLoader().load();
        final GameMap map = engineConfig.getMapLoader().load();

        final MonsterFactory monsterFactory = new MonsterFactory(eventHandler, map.getGameGeometry(), monsterAnimationFactory);
        final SoldierFactory soldierFactory = new SoldierFactory(eventHandler, map.getGameGeometry(), soldierAnimationFactory);

        final Creatures creatures = new Creatures();

        final EnemySpawner enemySpawner = new EnemySpawner(eventHandler, monsterFactory, creatures);
        final SoldierSpawner soldierSpawner = new SoldierSpawner(creatures);

        final TowerFactory towerFactory = new TowerFactory(eventHandler, map.getGameGeometry(), towerIconFactory, towerSpriteFactory, projectileSpriteFactory, creatures);
        final Towers towers = new Towers(map.getGameGeometry());

        final Fight fight = new Fight(towers, creatures, enemySpawner, soldierSpawner);

        final World world = new World(castle, fight, map);

        final GameStatisticsHolder gameStatisticsHolder = new GameStatisticsHolder(eventHandler, castle, STARTING_GOLD_AMOUNT);

        final MouseInteractionHandler mouseInteractionHandler = new MouseInteractionHandler(eventHandler, gameStatisticsHolder, towerFactory, towers.getInteractionTarget());
        final SoldierSpawnInteractionHandler soldierSpawnInteractionHandler = new SoldierSpawnInteractionHandler(gameStatisticsHolder, soldierFactory, soldierSpawner.getInteractionTarget());

        attachSubscriber(eventHandler, fight);
        attachSubscriber(eventHandler, gameStatisticsHolder);
        attachSubscriber(eventHandler, mouseInteractionHandler);
        attachSubscriber(eventHandler, soldierSpawnInteractionHandler);

        final Loop mainLoop = new ExecutorServiceLoop(Delay.ratePerSecond(30));
        final TimeAwareLoop tickLoop = new TimeAwareLoop();
        final TimeAwareLoop repaintLoop = new TimeAwareLoop();
        final TimeAware delegatedRepaintLoop = new TickDelegator(SwingUtilities::invokeLater, repaintLoop);

        mainLoop.add(tickLoop);
        mainLoop.add(delegatedRepaintLoop);
        tickLoop.add(fight);

        final EventListener<GameWonEvent> gameWonEventListener = EventListener.of(e -> tickLoop.stop(), GameWonEvent.class);
        final EventListener<GameOverEvent> gameOverListener = EventListener.of(e -> tickLoop.stop(), GameOverEvent.class);

        eventHandler.add(gameWonEventListener);
        eventHandler.add(gameOverListener);

        return new ConfigurableGameEngine(mainLoop, tickLoop, eventHandler, eventHandler, repaintLoop, world, mouseInteractionHandler);
    }

    private static void attachSubscriber(EventRouter eventRouter, Subscriber subscriber) {
        eventRouter.addAll(subscriber.getEventListeners());
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
