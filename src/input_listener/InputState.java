package input_listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InputState {
    private final Map<Integer, Boolean> current = new ConcurrentHashMap<>();
    private final Map<Integer, Boolean> previous = new ConcurrentHashMap<>();

    public void nextFrame() {
        previous.clear();
        previous.putAll(current);
    }

    public void onKeyPress(int keyCode) {
        current.put(keyCode, true);
    }

    public void onKeyRelease(int keyCode) {
        current.put(keyCode, false);
    }

    public boolean isKeyPressed(int keyCode) {
        return current.getOrDefault(keyCode, false) && !previous.getOrDefault(keyCode, false);
    }
}