package input_listener;

import java.util.HashSet;
import java.util.Set;

public class MovementState {
    private final Set<GameMovement> gameMovements = new HashSet<>();

    public void addMovement(GameMovement movement) {
        gameMovements.add(movement);
    }

    public Set<GameMovement> getMovements() {
        return gameMovements;
    }

    public void reset() {
        gameMovements.clear();
    }
}
