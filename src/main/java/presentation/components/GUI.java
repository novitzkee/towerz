package presentation.components;

import lombok.Getter;
import presentation.loaders.GameEngine;

import javax.swing.*;
import java.awt.*;

import static presentation.config.Dimensions.*;

@Getter
public class GUI {

    private static final int JFRAME_X_SIZE = WORLD_SIZE_PX.getX() + SELECTION_WIDTH + JFRAME_X_BOUNDS;

    private static final int JFRAME_Y_SIZE = WORLD_SIZE_PX.getY() + JFRMAE_Y_BOUNDS;

    private final GameEngine gameEngine;

    private final WorldPanel worldPanel;

    private final SelectionPanel selectionPanel;

    private final TowerPanel towerPanel;

    private final StatisticsPanel statisticsPanel;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.worldPanel = new WorldPanel(gameEngine.getEventEmitter(), gameEngine.getWorldObject());
        this.selectionPanel = new SelectionPanel(gameEngine.getEventEmitter());
        this.towerPanel = new TowerPanel(gameEngine.getEventEmitter());
        this.statisticsPanel = new StatisticsPanel();

        gameEngine.getRepaintLoop().add(worldPanel::repaint);
    }

    public void start() {
        composeGUI();
        gameEngine.getRepaintLoop().start();
    }

    private void composeGUI() {
        selectionPanel.setPreferredSize(new Dimension(SELECTION_WIDTH, JFRAME_Y_SIZE));
        selectionPanel.setVisible(true);
        selectionPanel.setBackground(Color.LIGHT_GRAY);

        worldPanel.setPreferredSize(new Dimension(WORLD_SIZE_PX.getX(), WORLD_SIZE_PX.getY()));
        worldPanel.setVisible(true);

        JFrame frame = new JFrame("Tower defence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(JFRAME_X_SIZE, JFRAME_Y_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);

        frame.getContentPane().add(selectionPanel, BorderLayout.WEST);
        frame.getContentPane().add(worldPanel, BorderLayout.EAST);
    }
}
