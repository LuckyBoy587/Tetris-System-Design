package tetrominos;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel {
    private final GameEnvironment env;
    private final int ROWS;
    private final int COLS;
    private final Board board;
    private final int CELL_SIZE = 30;

    public GameUI(GameEnvironment env, JFrame frame) {
        this.env = env;
        board = env.getBoard();
        ROWS = board.getRows();
        COLS = board.getColumns();
        frame.setSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderBoard(g);
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
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void renderCurrentTetromino() {

    }
}
