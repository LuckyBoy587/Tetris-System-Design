package tetrominos;

import java.awt.*;
import java.util.Arrays;

public class Board {
    private final int rows;
    private final int columns;
    private final TetrominoType[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new TetrominoType[rows][columns];
    }
    
    public boolean isValidPosition(Tetromino tetromino) {
        for (Point point: tetromino.getPoints()) {
            int boardX = tetromino.getX() + point.x;
            int boardY = tetromino.getY() + point.y;

            if (boardY < 0) {
                continue; // Allow blocks to be above the board
            }

            if (boardX < 0 || boardX >= columns || boardY >= rows) {
                return false; // Out of bounds
            }

            if (grid[boardY][boardX] != null) {
                return false; // Collision with existing block
            }
        }
        return true;
    }

    public void placeTetromino(Tetromino tetromino) {
        for (Point point: tetromino.getPoints()) {
            int boardX = tetromino.getX() + point.x;
            int boardY = tetromino.getY() + point.y;
            grid[boardY][boardX] = tetromino.getType();
        }
    }

    public String toStringWithTetromino(Tetromino currentTetromino) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                boolean isCurrentTetrominoBlock = false;
                for (Point point : currentTetromino.getPoints()) {
                    int tetrominoX = currentTetromino.getX() + point.x;
                    int tetrominoY = currentTetromino.getY() + point.y;
                    if (tetrominoX == x && tetrominoY == y) {
                        isCurrentTetrominoBlock = true;
                        break;
                    }
                }
                if (isCurrentTetrominoBlock) {
                    sb.append(currentTetromino.getType().name().charAt(0));
                } else if (grid[y][x] != null) {
                    sb.append(grid[y][x].name().charAt(0));
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public TetrominoType getCell(int x, int y) {
        return grid[y][x];
    }

    public boolean isRowComplete(int row) {
        for (int x = 0; x < columns; x++) {
            if (grid[row][x] == null) {
                return false;
            }
        }
        return true;
    }

    public void pullDownRowsAbove(int row) {
        for (int y = row; y > 0; y--) {
            if (columns >= 0) System.arraycopy(grid[y - 1], 0, grid[y], 0, columns);
        }
        Arrays.fill(grid[0], null);
    }
}
