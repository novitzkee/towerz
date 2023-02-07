package game.tower.utils;

import engine.geometry.Vector2i;
import game.creature.Creature;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DistanceMap {

    private final List<Integer> tickDistances;

    private final List<Vector2i> mapPositions;

    public static DistanceMap empty() {
        return new DistanceMap(List.of(), List.of());
    }

    public Optional<Vector2i> getBestRendezvouzPosition(Creature creature) {
        for (int i = 0; i < tickDistances.size() - 1; i++) {
            final int currentTickDistance = tickDistances.get(i);
            final Vector2i currentMapPosition = mapPositions.get(i);

            if (isRendezvous(currentTickDistance, currentMapPosition, creature))
                return Optional.of(currentMapPosition);
        }

        return Optional.empty();
    }

    private boolean isRendezvous(int tickDistance, Vector2i aimingPosition, Creature creature) {
        final Vector2i predictedPosition = creature.getMapPositionInTicks(tickDistance);
        return aimingPosition.equals(predictedPosition);
    }
}
