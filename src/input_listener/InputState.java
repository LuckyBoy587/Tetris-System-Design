package input_listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InputState {
    private final Map<Integer, Boolean> current = new ConcurrentHashMap<>();
    private final Map<Integer, Boolean> previous = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Long> keyPressTime = new ConcurrentHashMap<>();

    public void nextFrame() {
        previous.clear();
        previous.putAll(current);
    }

    public void onKeyPress(int keyCode) {
        if (!current.getOrDefault(keyCode, false)) {
            keyPressTime.put(keyCode, System.currentTimeMillis());
            current.put(keyCode, true);
        }
    }

    public void onKeyRelease(int keyCode) {
        current.put(keyCode, false);
    }

    public boolean isKeyPressed(GameMovement gameKey) {
        int keyCode = gameKey.toKeyCode();
        return current.getOrDefault(keyCode, false) && !previous.getOrDefault(keyCode, false) && !isKeyHeldDown(gameKey);
    }

    public boolean isKeyHeldDown(GameMovement gameKey) {
        int keyCode = gameKey.toKeyCode();
        // Time in milliseconds to consider a key as held down
        int holdThresholdMillis = 300;
        return current.getOrDefault(keyCode, false) && System.currentTimeMillis() - keyPressTime.get(keyCode) >= holdThresholdMillis;
    }

}