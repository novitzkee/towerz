package com.github.towerz;

import com.github.towerz.game.engine.ConfigurableGameEngine;
import com.github.towerz.game.engine.EngineConfig;
import com.github.towerz.game.engine.GameEngine;
import com.github.towerz.game.engine.loaders.TowerIconFactory;
import com.github.towerz.presentation.components.GUI;
import com.github.towerz.presentation.loaders.map.TextFileCastleLoader;
import com.github.towerz.presentation.loaders.map.TextFileMapLoader;
import com.github.towerz.presentation.loaders.sprites.MonsterResourceAnimationFactory;
import com.github.towerz.presentation.loaders.sprites.SoldierResourceAnimationFactory;
import com.github.towerz.presentation.loaders.sprites.TowerResourceIconFactory;
import com.github.towerz.presentation.loaders.sprites.TowerResourceSpriteFactory;

public class Towerz {

    public static void main(String[] args) {
        final EngineConfig engineConfig = getEngineConfiguration();
        final GameEngine gameEngine = ConfigurableGameEngine.configure(engineConfig);
        final GUI gui = new GUI(gameEngine);
        gameEngine.start();
    }

    private static EngineConfig getEngineConfiguration() {
        final TextFileCastleLoader castleLoader = new TextFileCastleLoader();
        final TextFileMapLoader mapLoader = new TextFileMapLoader();
        final MonsterResourceAnimationFactory monsterAnimationFactory = new MonsterResourceAnimationFactory();
        final SoldierResourceAnimationFactory soldierAnimationFactory = new SoldierResourceAnimationFactory();
        final TowerIconFactory towerIconFactory = new TowerResourceIconFactory();
        final TowerResourceSpriteFactory towerSpriteFactory = new TowerResourceSpriteFactory();

        return EngineConfig.builder()
                .castleLoader(castleLoader)
                .mapLoader(mapLoader)
                .monsterAnimationFactory(monsterAnimationFactory)
                .soldierAnimationFactory(soldierAnimationFactory)
                .towerIconFactory(towerIconFactory)
                .towerSpriteFactory(towerSpriteFactory)
                .projectileSpriteFactory(towerSpriteFactory)
                .build();
    }
}
