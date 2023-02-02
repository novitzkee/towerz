package presentation.components;

import engine.events.EventEmitter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;

import static presentation.components.GUI.JFRAME_Y_SIZE;
import static presentation.config.Dimensions.SELECTION_WIDTH;


@RequiredArgsConstructor
public class SidePanel extends JPanel {

    private final SelectionPanel selectionPanel;

    private final TowerPanel towerPanel;

    private final StatisticsPanel statisticsPanel;

    public SidePanel(EventEmitter eventEmitter) {
        this.selectionPanel = new SelectionPanel(eventEmitter);
        this.towerPanel = new TowerPanel(eventEmitter);
        this.statisticsPanel = new StatisticsPanel();
    }

    public void compose() {
        selectionPanel.compose();
        towerPanel.compose();
        statisticsPanel.compose();

        setPreferredSize(new Dimension(SELECTION_WIDTH, JFRAME_Y_SIZE));
        setVisible(true);
        setBackground(Color.LIGHT_GRAY);

        setLayout(new GridBagLayout());
        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.fill = GridBagConstraints.VERTICAL;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;

        add(selectionPanel, layoutConstraints);
        add(towerPanel, layoutConstraints);
        add(statisticsPanel, layoutConstraints);
    }
}
