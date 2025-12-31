import input_listener.GameMovement;
import input_listener.InputState;
import input_listener.KeyboardListener;
import input_listener.MovementState;
import schedulers.FrameScheduler;
import schedulers.GameLoop;
import schedulers.Interval;
import tetrominos.GameEnvironment;
import tetrominos.GameUI;

import javax.swing.*;
import java.awt.*;

void main() {
    final int ROWS = 15;
    final int COLS = 10;
    GameLoop loop = new GameLoop();
    FrameScheduler scheduler = loop.getScheduler();
    InputState inputState = new InputState();
    GameEnvironment env = new GameEnvironment(ROWS, COLS);
    JFrame frame = new JFrame("Game");
    GameUI ui = new GameUI(env);
    MovementState movementState = new MovementState();
    frame.add(ui);
    frame.pack();
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addKeyListener(new KeyboardListener(inputState));
    frame.setVisible(true);

    scheduler.scheduleEvery(500, Interval.MILLISECONDS, () -> {
        if (env.isGameOver()) {
            System.out.println("Game Over!");
            loop.stop();
        }
        env.gravityUpdate();
    });

    scheduler.scheduleEvery(1, Interval.FRAMES, () -> {
        for (GameMovement movement: GameMovement.values()) {
            if (inputState.isKeyPressed(movement)) {
                movementState.addMovement(movement);
            }
        }
        inputState.nextFrame();
    });

    scheduler.scheduleEvery(5, Interval.FRAMES, () -> {
        for (GameMovement movement: GameMovement.values()) {
            if (inputState.isKeyHeldDown(movement)) {
                movementState.addMovement(movement);
            }
        }
    });

    scheduler.scheduleEvery(1, Interval.FRAMES, () -> {
        env.update(movementState.getMovements());
        movementState.reset();
    });

    scheduler.scheduleEvery(1, Interval.FRAMES, ui::repaint);
    new Thread(loop).start();
}
