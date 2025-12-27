package tetrominos;

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
        currentTetromino.moveDown();
        handleIfLanded();
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
        board.placeTetromino(currentTetromino);
        spawnNewTetromino();
    }

    private void handleIfLanded() {
        currentTetromino.moveDown();
        boolean hasLanded = !board.isValidPosition(currentTetromino);
        currentTetromino.moveUp();
        if (hasLanded) {
            board.placeTetromino(currentTetromino);
            spawnNewTetromino();
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
}
