package com.github.towerz.game.engine;

import com.github.towerz.game.engine.loaders.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class EngineConfig {

    @NonNull
    private final CastleLoader castleLoader;

    @NonNull
    private final MapLoader mapLoader;

    @NonNull
    private final MonsterAnimationFactory monsterAnimationFactory;

    @NonNull
    private final SoldierAnimationFactory soldierAnimationFactory;

    @NonNull
    private final TowerIconFactory towerIconFactory;

    @NonNull
    private final TowerSpriteFactory towerSpriteFactory;

    private final ProjectileSpriteFactory projectileSpriteFactory;
}
