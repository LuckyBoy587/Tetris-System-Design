package events;

public record GameEvent(EventType type, Object payload) {
    public GameEvent(EventType type) {
        this(type, null);
    }
}