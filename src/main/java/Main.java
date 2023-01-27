import engine.events.ReportingEventEmitter;
import engine.geometry.Direction;
import engine.geometry.Vector2i;
import engine.graphics.Paintable;
import engine.graphics.Sprite;
import presentation.components.WorldPanel;
import presentation.sprites.MapSpriteFactory;

import javax.swing.*;
import java.awt.*;

import static presentation.config.Dimensions.*;

public class Main {

    private static final MapSpriteFactory MAP_SPRITE_FACTORY = new MapSpriteFactory();

    private static final int JFRAME_X_SIZE = WORLD_SIZE_PX.getX() + SELECTION_WIDTH + JFRAME_X_BOUNDS;

    private static final int JFRAME_Y_SIZE = WORLD_SIZE_PX.getY() + JFRMAE_Y_BOUNDS;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tower defence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(JFRAME_X_SIZE, JFRAME_Y_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setPreferredSize(new Dimension(SELECTION_WIDTH, JFRAME_Y_SIZE));
        selectionPanel.setVisible(true);
        selectionPanel.setBackground(Color.LIGHT_GRAY);

        WorldPanel worldPanel = new WorldPanel(new ReportingEventEmitter(), new SpriteTest());
        worldPanel.setPreferredSize(new Dimension(WORLD_SIZE_PX.getX(), WORLD_SIZE_PX.getY()));
        worldPanel.setVisible(true);

        frame.getContentPane().add(selectionPanel, BorderLayout.WEST);
        frame.getContentPane().add(worldPanel, BorderLayout.EAST);

        worldPanel.repaint();
    }

    private static class SpriteTest implements Paintable {
        @Override
        public void draw(Graphics2D graphics2D) {
            final Vector2i grassPosition = new Vector2i(0 ,0).map(position -> position * TILE_DIMENSIONS_PX);
            final Sprite grass = MAP_SPRITE_FACTORY.getGrassSprite();
            grass.draw(grassPosition, graphics2D);

            final Vector2i grassPosition1 = new Vector2i(14 ,14).map(position -> position * TILE_DIMENSIONS_PX);
            grass.draw(grassPosition1, graphics2D);

            final Vector2i pathPosition = new Vector2i(1 ,0).map(position -> position * TILE_DIMENSIONS_PX);
            final Sprite path = MAP_SPRITE_FACTORY.getPathSprite();
            path.draw(pathPosition, graphics2D);

            final Vector2i rockSpritePosition = new Vector2i(3, 0).map(position -> position * TILE_DIMENSIONS_PX);
            final Sprite rockSprite = MAP_SPRITE_FACTORY.getRockSprite();
            rockSprite.draw(rockSpritePosition, graphics2D);

            final Vector2i pathTurnSpritePosition = new Vector2i(4 ,0).map(position -> position * TILE_DIMENSIONS_PX);
            final Sprite pathTurnSprite = MAP_SPRITE_FACTORY.getPathTurnSprites().get(Direction.UP);
            pathTurnSprite.draw(pathTurnSpritePosition, graphics2D);
        }
    }
}
