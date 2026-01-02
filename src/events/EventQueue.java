package events;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue {
    private final Queue<GameEvent> queue = new ConcurrentLinkedQueue<>();

    public void publish(GameEvent event) {
        queue.add(event);
    }

    public GameEvent poll() {
        return queue.poll();
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
