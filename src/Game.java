import input_listener.InputState;
import input_listener.KeyboardListener;
import schedulers.FrameScheduler;
import schedulers.GameLoop;
import schedulers.Interval;
import tetrominos.GameEnvironment;
import tetrominos.GameUI;

import javax.swing.*;
import java.awt.event.KeyEvent;

void main() {
    final int ROWS = 15;
    final int COLS = 10;
    GameLoop loop = new GameLoop();
    FrameScheduler scheduler = loop.getScheduler();
    InputState inputState = new InputState();
    GameEnvironment env = new GameEnvironment(ROWS, COLS);
    JFrame frame = new JFrame("Game");
    GameUI ui = new GameUI(env, frame);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addKeyListener(new KeyboardListener(inputState));
    frame.setVisible(true);

    scheduler.scheduleEvery(500, Interval.MILLISECONDS, () -> {
        env.gravityUpdate();
        System.out.println(env);
        if (env.isGameOver()) {
            System.out.println("Game Over!");
            loop.stop();
        }
    });

    scheduler.scheduleEvery(1, Interval.FRAMES, () -> {
        if (inputState.isKeyPressed(KeyEvent.VK_LEFT)) {
            env.moveLeft();
        }
        if (inputState.isKeyPressed(KeyEvent.VK_RIGHT)) {
            env.moveRight();
        }
        if (inputState.isKeyPressed(KeyEvent.VK_SPACE)) {
            env.softDrop();
        }
        inputState.nextFrame();
    });

    new Thread(loop).start();
}
