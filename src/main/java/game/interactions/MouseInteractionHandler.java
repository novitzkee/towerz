package game.interactions;

import engine.events.EventEmitter;
import engine.events.EventListener;
import engine.events.Subscriber;
import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import engine.traits.Upgradeable;
import game.events.interaction.PricedSelection;
import game.events.interaction.input.GameMapClickEvent;
import game.events.interaction.input.GameMapHoverEvent;
import game.events.interaction.tower.*;
import game.interactions.targets.TowerBuildingInteractionTarget;
import game.tower.Tower;
import game.tower.TowerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MouseInteractionHandler implements Paintable, Subscriber {

    private static final Vector2i SELECTION_RECT_SIZE = new Vector2i(64, 64);

    private static final Color ACTIVE_SELECTION_COLOR = new Color(100, 50, 50, 192);

    private static final Color INACTIVE_SELECTION_COLOR = new Color(50, 100, 50, 64);

    private final EventEmitter eventEmitter;

    private final GameStatisticsHolder gameStatisticsHolder;

    private final TowerFactory towerFactory;

    private final TowerBuildingInteractionTarget towerBuildingInteractionTarget;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            EventListener.of(this::handleTowerSelectionEvent, TowerBuildSelectionChangedEvent.class),
            EventListener.of(this::handleHoverEvent, GameMapHoverEvent.class),
            EventListener.of(this::handleClickEvent, GameMapClickEvent.class),
            EventListener.of(this::handleTowerUpgradeEvent, TowerUpgradeEvent.class),
            EventListener.of(this::handleTowerSellEvent, TowerSellEvent.class));

    private Vector2i selectionPosition;

    private PricedSelection<TowerType> currentTowerSelection = PricedSelection.empty();

    @Override
    public void draw(DrawingTarget drawingTarget) {
        if (!isBuildingActive()) return;

        final Rect2i selectionRect = new Rect2i(selectionPosition, SELECTION_RECT_SIZE);
        drawingTarget.drawRect(selectionRect, getDrawingColor(), DrawingPositioning.RELATIVE);
    }

    private Color getDrawingColor() {
        return towerBuildingInteractionTarget.canPlace(selectionPosition) ?
                INACTIVE_SELECTION_COLOR :
                ACTIVE_SELECTION_COLOR;
    }

    private void handleTowerSelectionEvent(TowerBuildSelectionChangedEvent towerBuildSelectionChangedEvent) {
        currentTowerSelection = towerBuildSelectionChangedEvent.currentSelection();
    }

    private void handleHoverEvent(GameMapHoverEvent gameMapHoverEvent) {
        selectionPosition = gameMapHoverEvent.position();
    }

    private void handleClickEvent(GameMapClickEvent gameMapClickEvent) {
        final Vector2i clickPosition = gameMapClickEvent.position();
        if (isBuildingActive()) {
            build(clickPosition);
        } else {
            select(clickPosition);
        }
    }

    private boolean isBuildingActive() {
        return currentTowerSelection.selection() != null && gameStatisticsHolder.canPurchase(currentTowerSelection.price());
    }

    private void build(Vector2i position) {
        if (currentTowerSelection.selection() == null || !towerBuildingInteractionTarget.canPlace(position)) return;

        final Tower tower = switch (currentTowerSelection.selection()) {
            case ARROW -> towerFactory.createArrowTower(position);
            case ELECTRIC -> towerFactory.createElectricTower(position);
            case CANDY -> towerFactory.createCandyTower(position);
            case BASTION -> towerFactory.createBastionTower(position);
        };

        gameStatisticsHolder.purchase(currentTowerSelection.price());
        towerBuildingInteractionTarget.place(tower);
    }

    private void select(Vector2i position) {
        final Optional<Tower> towerOptional = towerBuildingInteractionTarget.get(position);
        notifyTowerSelectionChanged(towerOptional.map(Tower::getUpgradeable).orElse(null));
    }

    private void handleTowerUpgradeEvent(TowerUpgradeEvent towerUpgradeEvent) {
        final Upgradeable towerUpgradeable = towerUpgradeEvent.tower();

        if (!gameStatisticsHolder.canPurchase(towerUpgradeable.getUpgradePrice())) return;

        gameStatisticsHolder.purchase(towerUpgradeable.getUpgradePrice());
        towerBuildingInteractionTarget.get(towerUpgradeable.getPosition()).ifPresent(Tower::upgrade);
        notifyTowerSelectionChanged(towerUpgradeable);
    }

    private void handleTowerSellEvent(TowerSellEvent towerSellEvent) {
        final Upgradeable towerUpgradeable = towerSellEvent.tower();

        if(towerBuildingInteractionTarget.get(towerUpgradeable.getPosition()).isPresent()) {
            gameStatisticsHolder.sell(towerUpgradeable.getSellPrice());
            towerBuildingInteractionTarget.remove(towerUpgradeable.getPosition());
            notifyTowerSelectionChanged(null);
        }
    }

    private void notifyTowerSelectionChanged(Upgradeable upgradeable) {
        final TowerUpgradeSelectionChangedEvent event = new TowerUpgradeSelectionChangedEvent(upgradeable);
        eventEmitter.emit(event);
    }
}
