import events.EventQueue;
import events.EventType;
import events.GameEvent;
import input_listener.InputState;
import input_listener.InputSystem;
import input_listener.KeyboardListener;
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
    
    // Core Systems
    EventQueue eventQueue = new EventQueue();
    GameEnvironment env = new GameEnvironment(ROWS, COLS);
    InputState inputState = new InputState();
    InputSystem inputSystem = new InputSystem(inputState, eventQueue);
    // UI Setup
    JFrame frame = new JFrame("Game");
    GameUI ui = new GameUI(env);
    frame.add(ui);
    frame.pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addKeyListener(new KeyboardListener(inputState));
    frame.setVisible(true);

    // Loop & Scheduler
    GameLoop loop = new GameLoop();
    FrameScheduler scheduler = loop.getScheduler();

    // 1. Gravity (Timer-based event generation)
    scheduler.scheduleEvery(500, Interval.MILLISECONDS, () -> eventQueue.publish(new GameEvent(EventType.GRAVITY_TICK)));

    // 2. Input Polling (Generates Events)
    scheduler.scheduleEvery(1, Interval.FRAMES, inputSystem::updateKeyPress); // On press
    scheduler.scheduleEvery(5, Interval.FRAMES, inputSystem::updateKeyHeld); // On hold

    // 3. Event Processing (The "Event Loop" part)
    scheduler.scheduleEvery(1, Interval.FRAMES, () -> {
        while (!eventQueue.isEmpty()) {
            GameEvent event = eventQueue.poll();
            
            // Global handlers or dispatch to components
            if (event.type() == EventType.GAME_OVER) {
                System.out.println("Game Over!");
                loop.stop();
            } else {
                env.onEvent(event);
            }
        }
        
        // Check game-overstate *after* processing events (or could be an event itself)
        if (env.isGameOver()) {
             // We could publish a GAME_OVER event here to be handled next frame, 
             // but for simplicity we stop immediately or print.
             System.out.println("Game Over!");
             loop.stop();
        }
    });

    scheduler.scheduleEvery(1, Interval.FRAMES, env::onFrameTick);
    // 4. Render
    scheduler.scheduleEvery(1, Interval.FRAMES, ui::repaint);

    new Thread(loop).start();
}
