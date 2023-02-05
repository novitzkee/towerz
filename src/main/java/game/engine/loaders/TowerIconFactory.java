package game.engine.loaders;

import game.tower.TowerLevel;

import javax.swing.*;
import java.util.Map;

public interface TowerIconFactory {

    Map<TowerLevel, ImageIcon> getArrowTowerIcons();

    Map<TowerLevel, ImageIcon> getElectricTowerIcons();

    Map<TowerLevel, ImageIcon> getCandyTowerIcons();

    Map<TowerLevel, ImageIcon> getBastionTowerIcons();
}
