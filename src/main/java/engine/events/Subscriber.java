package engine.events;

import java.util.List;

public interface Subscriber {
    List<EventListener<?>> getEventListeners();
}
