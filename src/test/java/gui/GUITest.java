package gui;

import presentation.components.GUI;
import presentation.loaders.GameEngine;

public class GUITest {

    public static void main(String[] args) {
        final GameEngine testGameEngine = new TestGameEngine();
        final GUI gui = new GUI(testGameEngine);
        gui.start();
    }
}
