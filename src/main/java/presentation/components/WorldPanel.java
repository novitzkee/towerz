package presentation.components;

import engine.events.EventEmitter;
import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import game.events.GameMapClickEvent;
import game.events.GameMapHoverEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static presentation.config.Dimensions.TILE_DIMENSIONS_PX;

public class WorldPanel extends JPanel {

    private final EventEmitter eventEmitter;

    private final Paintable worldPaintable;

    public WorldPanel(EventEmitter eventEmitter, Paintable worldPaintable) {
        this.eventEmitter = eventEmitter;
        this.worldPaintable = worldPaintable;

        addMouseListener(new MouseClickEmitter());
        addMouseMotionListener(new MouseMovementEmitter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        worldPaintable.draw((Graphics2D) g);
    }

    private Vector2i translatePosition(int realX, int realY) {
        return new Vector2i(realX, realY).map(position -> position / TILE_DIMENSIONS_PX);
    }

    private class MouseClickEmitter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            final Vector2i mapClickPosition = translatePosition(e.getX(), e.getY());
            final GameMapClickEvent mapClickEvent = new GameMapClickEvent(mapClickPosition);
            eventEmitter.emit(mapClickEvent);
        }
    }

    private class MouseMovementEmitter extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            final Vector2i mapHoverPosition = translatePosition(e.getX(), e.getY());
            final GameMapHoverEvent mapHoverEvent = new GameMapHoverEvent(mapHoverPosition);
            eventEmitter.emit(mapHoverEvent);
        }
    }
}
