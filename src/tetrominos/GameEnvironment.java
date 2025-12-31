package tetrominos;

import input_listener.GameMovement;

import java.util.Set;

public class GameEnvironment {
    private final Board board;
    private Tetromino currentTetromino = Tetromino.randomTetromino(4, 0);

    public GameEnvironment(int rows, int columns) {
        this.board = new Board(rows, columns);
    }

    public Board getBoard() {
        return board;
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public void gravityUpdate() {
        handleIfLanded();
        currentTetromino.moveDown();
    }

    public void moveLeft() {
        currentTetromino.moveLeft();
        if (!board.isValidPosition(currentTetromino)) {
            currentTetromino.moveRight();
        }
    }
     public void moveRight() {
        currentTetromino.moveRight();
        if (!board.isValidPosition(currentTetromino)) {
            currentTetromino.moveLeft();
        }
    }

    public void rotate() {
        currentTetromino.rotateClockwise();
        if (!board.isValidPosition(currentTetromino)) {
            currentTetromino.rotateCounterClockwise();
        }
    }

    public void softDrop() {
        while (board.isValidPosition(currentTetromino)) {
            currentTetromino.moveDown();
        }
        currentTetromino.moveUp();
        onTetrominoLanded();
    }

    private void handleIfLanded() {
        currentTetromino.moveDown();
        boolean hasLanded = !board.isValidPosition(currentTetromino);
        currentTetromino.moveUp();
        if (hasLanded) {
            onTetrominoLanded();
        }
    }

    private void onTetrominoLanded() {
        board.placeTetromino(currentTetromino);
        clearLines();
        spawnNewTetromino();
    }

    private void clearLines() {
        // Implementation for clearing completed lines from the board
        for (int row = board.getRows() - 1; row >= 0; row--) {
            while (board.isRowComplete(row)) {
                board.pullDownRowsAbove(row);
            }
        }
    }

    public void spawnNewTetromino() {
        currentTetromino = Tetromino.randomTetromino(4, 0);
    }

    @Override
    public String toString() {
        return "==================\n" + board.toStringWithTetromino(currentTetromino) + "\n=================";
    }

    public boolean isGameOver() {
        return !board.isValidPosition(currentTetromino);
    }

    public void update(Set<GameMovement> movementStates) {
        for (GameMovement movement : movementStates) {
            switch (movement) {
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
                case SPACE -> rotate();
                case DOWN -> softDrop();
            }
        }
    }
}
