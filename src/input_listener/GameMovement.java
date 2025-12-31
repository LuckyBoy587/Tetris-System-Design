package input_listener;

import static java.awt.event.KeyEvent.*;

public enum GameMovement {
    LEFT, RIGHT, DOWN, SPACE;

    public int toKeyCode() {
        return switch (this) {
            case LEFT -> VK_LEFT;
            case RIGHT -> VK_RIGHT;
            case DOWN -> VK_DOWN;
            case SPACE -> VK_SPACE;
        };
    }
}
