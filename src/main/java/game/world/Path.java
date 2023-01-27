package game.world;

import engine.events.EventListener;
import engine.events.EventRouter;
import engine.events.Subscriber;
import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import engine.time.TimeAware;
import game.events.GameMapClickEvent;
import game.events.ProjectileHitEvent;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.*;
import java.util.List;

@RequiredArgsConstructor
public class Path implements TimeAware, Paintable, Subscriber {

    private final Set<Vector2i> path;

    private final List<Object> collectibles = new ArrayList<>();

    private final List<EventListener<?>> listeners = List.of();

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void subscribe(EventRouter eventRouter) {

    }

    @Override
    public void unsubscribe(EventRouter eventRouter) {

    }

    private class PathClickListener implements EventListener<GameMapClickEvent> {

        @Override
        public void onEvent(GameMapClickEvent event) {
            if(!path.contains(event.position()))
                return;

        }

        @Override
        public Class<GameMapClickEvent> getEventClass() { return GameMapClickEvent.class; }
    }

    private class PathProjectileHitListener implements EventListener<ProjectileHitEvent> {

        @Override
        public void onEvent(ProjectileHitEvent event) {
            if(!path.contains(event.position()))
                return;
        }

        @Override
        public Class<ProjectileHitEvent> getEventClass() {
            return ProjectileHitEvent.class;
        }
    }
}
