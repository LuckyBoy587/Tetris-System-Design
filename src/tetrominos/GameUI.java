package tetrominos;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel {
    private final GameEnvironment env;
    private final int ROWS;
    private final int COLS;
    private final Board board;
    private final int CELL_SIZE = 40;

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
        renderCurrentTetromino(g);
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
                TetrominoType type = board.getCell(x, y);
                if (type != null) {
                    g.setColor(type.getColor());
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void renderCurrentTetromino(Graphics g) {
        Tetromino current = env.getCurrentTetromino();
        if (current != null) {
            g.setColor(current.getType().getColor());
            for (Point p : current.getPoints()) {
                int drawX = (current.getX() + p.x) * CELL_SIZE;
                int drawY = (current.getY() + p.y) * CELL_SIZE;
                g.fillRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
                g.setColor(current.getType().getColor());
            }
        }
    }
}
