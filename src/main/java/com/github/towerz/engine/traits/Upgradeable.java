package com.github.towerz.engine.traits;

import com.github.towerz.engine.geometry.Vector2i;

import javax.swing.*;

public interface Upgradeable {

    Vector2i getPosition();

    boolean canUpgrade();

    ImageIcon getCurrentIcon();

    ImageIcon getUpgradedIcon();

    int getUpgradePrice();

    int getSellPrice();
}
