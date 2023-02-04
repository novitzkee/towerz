import game.engine.ConfigurableGameEngine;
import game.engine.EngineConfig;
import game.engine.GameEngine;
import presentation.components.GUI;
import presentation.loaders.map.TextFileCastleLoader;
import presentation.loaders.map.TextFileMapLoader;
import presentation.loaders.sprites.CreatureResourceAnimationFactory;
import presentation.loaders.sprites.TowerResourceSpriteFactory;

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
        final CreatureResourceAnimationFactory creatureAnimationFactory = new CreatureResourceAnimationFactory();
        final TowerResourceSpriteFactory towerSpriteFactory = new TowerResourceSpriteFactory();

        return EngineConfig.builder()
                .castleLoader(castleLoader)
                .mapLoader(mapLoader)
                .monsterAnimationFactory(creatureAnimationFactory)
                .soldierAnimationFactory(creatureAnimationFactory)
                .towerSpriteFactory(towerSpriteFactory)
                .build();
    }
}
