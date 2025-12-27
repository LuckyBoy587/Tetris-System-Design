package tetrominos;

import java.awt.*;
import java.util.List;

public enum TetrominoType {
    I, O, T, S, Z, J, L;

    public Color getColor() {
        return switch (this) {
            case I -> Color.CYAN;
            case O -> Color.YELLOW;
            case T -> Color.MAGENTA;
            case S -> Color.GREEN;
            case Z -> Color.RED;
            case J -> Color.BLUE;
            case L -> Color.ORANGE;
        };
    }

    /**
     * Returns a list of rotation states.
     * Each rotation state is a list of 4 Points representing the blocks' local coordinates.
     */
    public List<List<Point>> getRotationPoints() {
        return switch (this) {
            case I -> List.of(
                List.of(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)),
                List.of(new Point(2, 0), new Point(2, 1), new Point(2, 2), new Point(2, 3)),
                List.of(new Point(0, 2), new Point(1, 2), new Point(2, 2), new Point(3, 2)),
                List.of(new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3))
            );
            case O -> List.of(
                List.of(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))
            );
            case T -> List.of(
                List.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)),
                List.of(new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)),
                List.of(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)),
                List.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2))
            );
            case S -> List.of(
                List.of(new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)),
                List.of(new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 1), new Point(2, 1), new Point(0, 2), new Point(1, 2)),
                List.of(new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2))
            );
            case Z -> List.of(
                List.of(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)),
                List.of(new Point(2, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)),
                List.of(new Point(0, 1), new Point(1, 1), new Point(1, 2), new Point(2, 2)),
                List.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2))
            );
            case J -> List.of(
                List.of(new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)),
                List.of(new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1, 2)),
                List.of(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)),
                List.of(new Point(1, 0), new Point(1, 1), new Point(0, 2), new Point(1, 2))
            );
            case L -> List.of(
                List.of(new Point(2, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)),
                List.of(new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)),
                List.of(new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)),
                List.of(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(1, 2))
            );
        };
    }
}

