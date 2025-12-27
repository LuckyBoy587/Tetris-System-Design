package input_listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private final InputState inputState;

    public KeyboardListener(InputState inputState) {
        this.inputState = inputState;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputState.onKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputState.onKeyRelease(e.getKeyCode());
    }
}
