package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventDispatcher {
    private final Map<Class<? extends GameEvent>, List<Consumer<? extends GameEvent>>> listeners = new HashMap<>();

    public void dispatch(GameEvent event) {
        List<Consumer<? extends GameEvent>> eventListeners = listeners.get(event.getClass());
        if (eventListeners == null) return;

        eventListeners.forEach(listener -> {
            @SuppressWarnings("unchecked")
            Consumer<GameEvent> consumer = (Consumer<GameEvent>) listener;
            consumer.accept(event);
        });
    }

    public <E extends GameEvent> void addEventListener(Class<E> eventClass, Consumer<E> listener) {
        listeners.computeIfAbsent(eventClass, _ -> new ArrayList<>())
                 .add(listener);
    }
}
