package tetrominos;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel {
    private final GameEnvironment env;
    private final int ROWS;
    private final int COLS;
    private final Board board;
    private final int CELL_SIZE = 30;

    public GameUI(GameEnvironment env) {
        this.env = env;
        board = env.getBoard();
        ROWS = board.getRows();
        COLS = board.getColumns();
        setPreferredSize(new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderGrid(g);
        renderBoard(g);
        renderTetromino(g, env.getCurrentTetromino(), false);
        renderTetromino(g, env.getShadowTetromino(), true);
    }

    private void renderGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int x = 0; x <= COLS; x++) {
            g.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, ROWS * CELL_SIZE);
        }
        for (int y = 0; y <= ROWS; y++) {
            g.drawLine(0, y * CELL_SIZE, COLS * CELL_SIZE, y * CELL_SIZE);
        }
    }

    private void renderBoard(Graphics g) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                Cell cell = board.getCell(x, y);
                renderCell(g, cell);
            }
        }
    }

    private void renderCell(Graphics g, Cell cell) {
        TetrominoType type = cell.getType();
        if (type != null) {
            int xCoords = (int) (cell.getScreenX() * CELL_SIZE);
            int yCoords = (int) (cell.getScreenY() * CELL_SIZE);
            g.setColor(type.getColor());
            g.fillRect(xCoords, yCoords, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(xCoords, yCoords, CELL_SIZE, CELL_SIZE);
        }
    }

    private void renderTetromino(Graphics g, Tetromino tetromino, boolean isShadow) {
        if (tetromino != null) {
            for (Point p : tetromino.getPoints()) {
                int drawX = (tetromino.getX() + p.x) * CELL_SIZE;
                int drawY = (tetromino.getY() + p.y) * CELL_SIZE;
                if (isShadow) {
                    g.setColor(getShadowColor(tetromino.getType().getColor()));
                } else {
                    g.setColor(tetromino.getType().getColor());
                }
                g.fillRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
                if (isShadow) {
                    g.setColor(getShadowColor(Color.BLACK));
                } else {
                    g.setColor(Color.BLACK);
                }
                g.drawRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private Color getShadowColor(Color color) {
        int alpha = (int) (0.25f * 255); // 25% transparent
        return new Color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                alpha
        );
    }
}
