package tetrominos;

import java.awt.*;

public class Board {
    private final int rows;
    private final int columns;
    private final Cell[][] dynamicGrid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.dynamicGrid = new Cell[rows][columns];
        initializeDynamicGrid();
    }

    private void initializeDynamicGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                dynamicGrid[i][j] = new Cell();
            }
        }
    }

    public boolean isValidPosition(Tetromino tetromino) {
        for (Point point : tetromino.getPoints()) {
            int boardX = tetromino.getX() + point.x;
            int boardY = tetromino.getY() + point.y;

            if (boardY < 0) {
                continue; // Allow blocks to be above the board
            }

            if (boardX < 0 || boardX >= columns || boardY >= rows) {
                return false; // Out of bounds
            }

            if (dynamicGrid[boardY][boardX].getType() != null) {
                return false; // Collision with existing block
            }
        }
        return true;
    }

    public void placeTetromino(Tetromino tetromino) {
        for (Point point : tetromino.getPoints()) {
            int boardX = tetromino.getX() + point.x;
            int boardY = tetromino.getY() + point.y;

            if (boardY >= 0) {
                Cell cell = dynamicGrid[boardY][boardX];
                cell.setType(tetromino.getType());
                cell.setSource(boardX, boardY);
                cell.setDestination(boardX, boardY);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell getCell(int x, int y) {
        return dynamicGrid[y][x];
    }

    public boolean isRowComplete(int row) {
        for (int x = 0; x < columns; x++) {
            if (dynamicGrid[row][x].getType() == null) {
                return false;
            }
        }
        return true;
    }

    public void pullDownRowsAbove(int row) {
        for (int i = row; i > 0; i--) {
            if (columns >= 0) {
                for (int j = 0; j < columns; j++) {
                    dynamicGrid[i][j] = dynamicGrid[i - 1][j];
                    if (dynamicGrid[i][j].getType() != null) {
                        dynamicGrid[i][j].setDestY(i);
                    }
                }
            }
        }
        for (int j = 0; j < columns; j++) {
            dynamicGrid[0][j] = new Cell();
        }
    }

    public boolean isTopReached() {
        for (int x = 0; x < columns; x++) {
            if (dynamicGrid[0][x].getType() != null) {
                return true;
            }
        }
        return false;
    }

    public void onFrameTick() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                dynamicGrid[i][j].update();
            }
        }
    }
}
