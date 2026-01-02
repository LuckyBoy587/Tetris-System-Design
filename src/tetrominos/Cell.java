package tetrominos;

public class Cell extends VisualPosition {
    private TetrominoType type;

    public Cell() {
        type = null;
    }

    public TetrominoType getType() {
        return type;
    }

    public void setType(TetrominoType type) {
        this.type = type;
    }

    protected double getScreenX() {
        return srcX;
    }

    protected double getScreenY() {
        return srcY;
    }
}
