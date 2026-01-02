package tetrominos;

import events.GameEvent;
import events.GameEventListener;

public class GameEnvironment implements GameEventListener {
    private final Board board;
    private Tetromino currentTetromino;

    public GameEnvironment(int rows, int columns) {
        board = new Board(rows, columns);
        spawnNewTetromino();
    }

    @Override
    public void onEvent(GameEvent event) {
        switch (event.type()) {
            case INPUT_LEFT -> moveLeft();
            case INPUT_RIGHT -> moveRight();
            case INPUT_ROTATE -> rotate();
            case INPUT_SOFT_DROP -> softDrop();
            case GRAVITY_TICK -> gravityUpdate();
        }
    }

    public void onFrameTick() {
        board.onFrameTick();
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
        currentTetromino = Tetromino.randomTetromino();
        int centeredX = (board.getColumns() - currentTetromino.getWidth()) / 2;
        currentTetromino.setX(centeredX);
    }

    public boolean isGameOver() {
        return board.isTopReached();
    }
}
