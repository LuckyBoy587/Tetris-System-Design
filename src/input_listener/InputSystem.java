package input_listener;

import events.EventQueue;
import events.EventType;
import events.GameEvent;

public class InputSystem {
    private final InputState inputState;
    private final EventQueue eventQueue;
    private final MovementState movementState; // Use MovementState to track repeat intervals if needed, or simplify

    public InputSystem(InputState inputState, EventQueue eventQueue) {
        this.inputState = inputState;
        this.eventQueue = eventQueue;
        this.movementState = new MovementState();
    }

    public void update() {
        // Simple logic: If key is pressed (newly), emit event.
        // If key is held, emit event periodically (managed by GameLoop or internal timer).
        // For this refactoring, we'll follow the existing pattern:
        // InputState tracks "pressed" (current frame) and "held" (duration).
        
        // Check for new presses
        for (GameMovement movement : GameMovement.values()) {
             if (inputState.isKeyPressed(movement)) {
                 publishEvent(movement);
             }
        }
        
        inputState.nextFrame();
    }
    
    public void updateHeld() {
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
