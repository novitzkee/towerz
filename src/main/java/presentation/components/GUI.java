package presentation.components;

import game.engine.GameEngine;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static presentation.config.Dimensions.*;

@Getter
public class GUI {

    public static final int JFRAME_X_SIZE = WORLD_SIZE_PX.getX() + SELECTION_WIDTH + JFRAME_X_BOUNDS;

    public static final int JFRAME_Y_SIZE = WORLD_SIZE_PX.getY() + JFRAME_Y_BOUNDS;

    private final WorldPanel worldPanel;

    private final SidePanel sidePanel;


    public GUI(GameEngine gameEngine) {
        this.worldPanel = new WorldPanel(gameEngine);
        this.sidePanel = new SidePanel(gameEngine.getEventEmitter());

        gameEngine.getRepaintLoop().add(worldPanel::repaint);
        composeGUI();
    }

    private void composeGUI() {
        sidePanel.compose();
        worldPanel.compose();

        JFrame frame = new JFrame("Tower defence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(JFRAME_X_SIZE, JFRAME_Y_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);

        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(worldPanel, BorderLayout.EAST);
    }
}
