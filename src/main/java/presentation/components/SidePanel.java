package presentation.components;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import lombok.RequiredArgsConstructor;
import presentation.components.resources.FontProvider;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static presentation.components.GUI.JFRAME_Y_SIZE;
import static presentation.config.Dimensions.SELECTION_WIDTH;


@RequiredArgsConstructor
public class SidePanel extends JPanel implements Subscriber {

    public static final Color SIDE_PANEL_COLOR = new Color(136,139,141);

    private final UpgradePanel upgradePanel;

    private final TowerPanel towerPanel;

    private final SoldierPanel soldierPanel;

    private final StatisticsPanel statisticsPanel;

    public SidePanel(EventEmitter eventEmitter) {
        this.upgradePanel = new UpgradePanel(eventEmitter);
        this.towerPanel = new TowerPanel(eventEmitter);
        this.soldierPanel = new SoldierPanel(eventEmitter);
        this.statisticsPanel = new StatisticsPanel();

        setFont(FontProvider.get());
    }

    public void compose() {
        upgradePanel.compose();
        towerPanel.compose();
        soldierPanel.compose();
        statisticsPanel.compose();

        setPreferredSize(new Dimension(SELECTION_WIDTH, JFRAME_Y_SIZE));
        setVisible(true);
        setBackground(SIDE_PANEL_COLOR);

        final JLabel titleLabel = new JLabel("Towerz");
        titleLabel.setFont(FontProvider.get().deriveFont(42f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());

        final GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.fill = GridBagConstraints.VERTICAL;
        layoutConstraints.weighty = 1;
        layoutConstraints.gridx = 0;

        add(titleLabel);
        add(upgradePanel, layoutConstraints);
        add(towerPanel, layoutConstraints);
        add(soldierPanel, layoutConstraints);
        add(statisticsPanel, layoutConstraints);
    }

    @Override
    public List<EventListener<?>> getEventListeners() {
        return statisticsPanel.getEventListeners();
    }
}
