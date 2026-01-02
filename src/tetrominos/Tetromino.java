package tetrominos;

import java.awt.*;
import java.util.List;

public class Tetromino implements Cloneable {
    private final TetrominoType type;
    private int _rotation_index = 0;
    private int x;
    private int y;
    private final List<List<Point>> rotationPoints;

    public Tetromino(TetrominoType type) {
        this.type = type;
        this.rotationPoints = type.getRotationPoints();
        this.y = -getMaxHeight();
    }

    public TetrominoType getType() {
        return type;
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

    public static Tetromino randomTetromino() {
        TetrominoType[] types = TetrominoType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return new Tetromino(types[randomIndex]);
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

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        int maxX = 0;
        for (Point point : getPoints()) {
            if (point.x > maxX) {
                maxX = point.x;
            }
        }
        return maxX + 1;
    }

    public int getMaxHeight() {
        int maxY = 0;
        for (Point point : getPoints()) {
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        return maxY + 1; // +1 because y starts from 0
    }

    @Override
    public Tetromino clone() {
        try {
            return (Tetromino) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}