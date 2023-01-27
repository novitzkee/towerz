package game.world;

import engine.events.EventEmitter;
import engine.traits.Container;
import game.creature.ICreature;
import game.creature.Spawner;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Fight implements Container<ICreature> {

    private final EventEmitter eventEmitter;

    private final Spawner spawner = new Spawner();

    private final Path path;

    private final List<ICreature> attackingCreatures = new ArrayList<>();

    private final List<ICreature> defendingCreatures = new ArrayList<>();

    @Override
    public void add(ICreature element) {

    }

    @Override
    public void remove(ICreature element) {

    }

    @Override
    public Stream<ICreature> getElements() {
        return null;
    }
}
