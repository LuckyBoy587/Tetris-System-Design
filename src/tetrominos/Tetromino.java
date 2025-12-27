package tetrominos;

import java.awt.*;
import java.util.List;

public class Tetromino {
    private final TetrominoType type;
    private int _rotation_index = 0;
    private int x;
    private int y;
    private final List<List<Point>> rotationPoints;

    public Tetromino(TetrominoType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.rotationPoints = type.getRotationPoints();
    }

    public TetrominoType getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Point> getPoints() {
       return rotationPoints.get(_rotation_index);
    }

    public void rotateClockwise() {
        _rotation_index = (_rotation_index + 1) % rotationPoints.size();
    }

    public void rotateCounterClockwise() {
        _rotation_index = (_rotation_index - 1 + rotationPoints.size()) % rotationPoints.size();
    }

    public static Tetromino randomTetromino(int x, int y) {
        TetrominoType[] types = TetrominoType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return new Tetromino(types[randomIndex], x, y);
    }

    public void moveDown() {
        this.y += 1;
    }

    public void moveUp() {
        this.y -= 1;
    }

    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight() {
        this.x += 1;
    }
}