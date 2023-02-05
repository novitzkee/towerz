package game.tower.utils;

import engine.geometry.Range;
import game.world.GameGeometry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TowerEngine {

    //private final Creatures creatures;

    private final GameGeometry gameGeometry;

    @Getter
    private Range pathRange;

    private int reloadCounter;


    public boolean isReloaded() {
        return true;
    }

    private void updateRange(int rangeRadius) {

    }
}
