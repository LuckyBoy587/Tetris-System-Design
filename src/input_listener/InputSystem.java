package input_listener;

import events.EventQueue;
import events.EventType;
import events.GameEvent;

public class InputSystem {
    private final InputState inputState;
    private final EventQueue eventQueue;

    public InputSystem(InputState inputState, EventQueue eventQueue) {
        this.inputState = inputState;
        this.eventQueue = eventQueue;
    }

    public void updateKeyPress() {
        for (GameMovement movement : GameMovement.values()) {
             if (inputState.isKeyPressed(movement)) {
                 publishEvent(movement);
             }
        }
        
        inputState.nextFrame();
    }
    
    public void updateKeyHeld() {
        for (GameMovement movement : GameMovement.values()) {
            if (inputState.isKeyHeldDown(movement)) {
                publishEvent(movement);
            }
        }
    }

    private void publishEvent(GameMovement movement) {
        EventType type = switch (movement) {
            case LEFT -> EventType.INPUT_LEFT;
            case RIGHT -> EventType.INPUT_RIGHT;
            case SPACE -> EventType.INPUT_ROTATE;
            case DOWN -> EventType.INPUT_SOFT_DROP;
        };
        eventQueue.publish(new GameEvent(type));
    }
}
