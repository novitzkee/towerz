package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import engine.geometry.Vector2i;
import engine.graphics.Graphics2DTarget;
import engine.graphics.Paintable;
import game.engine.GameEngine;
import game.events.interaction.gameplay.GameOverEvent;
import game.events.interaction.gameplay.GameWonEvent;
import game.events.interaction.input.GameMapClickEvent;
import game.events.interaction.input.GameMapHoverEvent;
import game.interactions.MouseInteractionHandler;
import lombok.Getter;
import presentation.components.resources.FontProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static presentation.config.Dimensions.*;

public class WorldPanel extends JPanel implements Subscriber {

    private final EventEmitter eventEmitter;

    private final Paintable worldPaintable;

    private final MouseInteractionHandler mouseInteractionHandler;

    private final JLabel gameOverLabel;

    private final JLabel gameWonLabel;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(e -> displayGameOver(), GameOverEvent.class),
            EventListener.of(e -> displayGameWon(), GameWonEvent.class));

    public WorldPanel(GameEngine gameEngine) {
        this.eventEmitter = gameEngine.getEventEmitter();
        this.worldPaintable = gameEngine.getWorldObject();
        this.mouseInteractionHandler = gameEngine.getMouseInteractionHandler();

        this.gameOverLabel = new JLabel("Game Over!");
        this.gameWonLabel = new JLabel("Victory!");

        compose();

        addMouseListener(new MouseClickEmitter());
        addMouseMotionListener(new MouseMovementEmitter());
    }

    private void compose() {
        setPreferredSize(new Dimension(WORLD_SIZE_PX.getX(), WORLD_SIZE_PX.getY()));
        setVisible(true);

        gameOverLabel.setFont(FontProvider.get().deriveFont(100f));
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);

        gameWonLabel.setFont(FontProvider.get().deriveFont(100f));
        gameWonLabel.setHorizontalAlignment(JLabel.CENTER);
        gameWonLabel.setVerticalAlignment(JLabel.CENTER);

        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2DTarget drawingTarget = new Graphics2DTarget((Graphics2D) g, WORLD_TO_REAL_POSITION_TRANSLATION);
        worldPaintable.draw(drawingTarget);
        mouseInteractionHandler.draw(drawingTarget);
    }

    private Vector2i translatePosition(int realX, int realY) {
        return new Vector2i(realX, realY).div(TILE_DIMENSIONS_PX);
    }

    private void displayGameOver() {
        add(gameOverLabel, BorderLayout.CENTER);
        revalidate();
    }

    private void displayGameWon() {
        add(gameWonLabel, BorderLayout.CENTER);
        revalidate();
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
