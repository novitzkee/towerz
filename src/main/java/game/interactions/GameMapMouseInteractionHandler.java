package game.interactions;

import engine.events.EventListener;
import engine.events.Subscriber;
import engine.geometry.Rect2i;
import engine.geometry.Vector2i;
import engine.graphics.DrawingPositioning;
import engine.graphics.DrawingTarget;
import engine.graphics.Paintable;
import game.events.interaction.*;
import game.interactions.targets.TowerBuildingInteractionTarget;
import game.tower.Tower;
import game.tower.TowerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
public class GameMapMouseInteractionHandler implements Paintable, Subscriber {

    private static final Vector2i SELECTION_RECT_SIZE = new Vector2i(64, 64);

    private static final Color ACTIVE_SELECTION_COLOR = new Color(100, 50, 50, 192);

    private static final Color INACTIVE_SELECTION_COLOR = new Color(50, 100, 50, 64);

    private final GameStatisticsHolder gameStatisticsHolder;

    private final TowerFactory towerFactory;

    private final TowerBuildingInteractionTarget towerBuildingInteractionTarget;

    @Getter
    private final List<EventListener<?>> eventListeners = List.of(
            new TowerSelectionChangeListener(),
            new MapMouseHoverEventListener(),
            new MapMouseClickEventListener());

    private Vector2i selectionPosition;

    private PricedSelection<TowerType> currentTowerSelection = PricedSelection.empty();

    @Override
    public void draw(DrawingTarget drawingTarget) {
        if (!isBuildingActive()) return;

        final Rect2i selectionRect = new Rect2i(selectionPosition, SELECTION_RECT_SIZE);
        drawingTarget.drawRect(selectionRect, getDrawingColor(), DrawingPositioning.RELATIVE);
    }

    private void handleClick(Vector2i position) {
        if (isBuildingActive()) {
            build(position);
        } else {
            select(position);
        }
    }

    private boolean isBuildingActive() {
        return currentTowerSelection.selection() != null && gameStatisticsHolder.canPurchase(currentTowerSelection);
    }

    private void build(Vector2i position) {
        if(currentTowerSelection.selection() == null || !towerBuildingInteractionTarget.canPlace(position)) return;

        final Tower tower = switch (currentTowerSelection.selection()) {
            case ARROW -> towerFactory.createArrowTower(position);
            case ELECTRIC -> towerFactory.createElectricTower(position);
            case CANDY -> towerFactory.createCandyTower(position);
            case BASTION -> towerFactory.createBastionTower(position);
        };

        gameStatisticsHolder.purchase(currentTowerSelection);
        towerBuildingInteractionTarget.place(tower);
    }

    private void select(Vector2i position) {
        // TODO
    }

    private Color getDrawingColor() {
        return towerBuildingInteractionTarget.canPlace(selectionPosition) ?
                INACTIVE_SELECTION_COLOR :
                ACTIVE_SELECTION_COLOR;
    }

    private void updateSelection(PricedSelection<TowerType> newSelection) {
        currentTowerSelection = newSelection;
    }

    private void updatePosition(Vector2i newPosition) {
        selectionPosition = newPosition;
    }

    private class TowerSelectionChangeListener implements EventListener<TowerBuildSelectionChangedEvent> {

        @Override
        public void onEvent(TowerBuildSelectionChangedEvent event) {
            updateSelection(event.currentSelection());
        }

        @Override
        public Class<TowerBuildSelectionChangedEvent> getEventClass() {
            return TowerBuildSelectionChangedEvent.class;
        }
    }

    private class MapMouseHoverEventListener implements EventListener<GameMapHoverEvent> {

        @Override
        public void onEvent(GameMapHoverEvent event) {
            updatePosition(event.position());
        }

        @Override
        public Class<GameMapHoverEvent> getEventClass() {
            return GameMapHoverEvent.class;
        }
    }

    private class MapMouseClickEventListener implements EventListener<GameMapClickEvent> {

        @Override
        public void onEvent(GameMapClickEvent event) {
            handleClick(event.position());
        }

        @Override
        public Class<GameMapClickEvent> getEventClass() {
            return GameMapClickEvent.class;
        }
    }
}
