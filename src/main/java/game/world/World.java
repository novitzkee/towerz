package game.world;

import engine.graphics.Paintable;
import engine.traits.Container;
import engine.traits.Removable;
import game.castle.Castle;
import game.tower.Projectile;
import game.tower.Tower;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class World implements Paintable, Container<Removable> {

    private final Castle castle;

    //private final Path path;

    private final Fight flight;

    private final GameMap gameMap;

    private final Set<Tower> towers = new HashSet<>();

    private final Set<Projectile> projectiles = new HashSet<>();

    @Override
    public void draw(Graphics2D graphics) {
        gameMap.draw(graphics);
        //path.draw(graphics);
        castle.draw(graphics);
        towers.forEach(tower -> tower.draw(graphics));

        projectiles.forEach(projectile -> projectile.draw(graphics));
    }

    @Override
    public void add(Removable element) {

    }

    @Override
    public void remove(Removable element) {

    }

    @Override
    public Stream<Removable> getElements() {
        return null;
    }
}
