import game.engine.ConfigurableGameEngine;
import game.engine.EngineConfig;
import game.engine.GameEngine;
import game.engine.loaders.TowerIconFactory;
import presentation.components.GUI;
import presentation.loaders.map.TextFileCastleLoader;
import presentation.loaders.map.TextFileMapLoader;
import presentation.loaders.sprites.MonsterResourceAnimationFactory;
import presentation.loaders.sprites.SoldierResourceAnimationFactory;
import presentation.loaders.sprites.TowerResourceIconFactory;
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
                .build();
    }
}
