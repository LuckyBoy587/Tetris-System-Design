package tetrominos;

/**
 * Represents a position that transitions visually from a source coordinate to a destination
 * coordinate over time, influenced by velocity and acceleration.
 */
public class VisualPosition {
    protected static final double ACCELERATION = 0.01;
    protected double srcX, srcY, destX, destY;
    protected double velocity = 0;

    public void setDestination(double x, double y) {
        this.destX = x;
        this.destY = y;
    }

    public void setSource(double x, double y) {
        this.srcX = x;
        this.srcY = y;
    }

    public void setDestY(double destY) {
        this.destY = destY;
    }

    public void update() {
        if (srcX == destX && srcY == destY) {
            velocity = 0;
            return;
        }

        velocity += ACCELERATION;

        double delta = calculateMovementDelta();
        double distX = destX - srcX;
        double distY = destY - srcY;
        double distance = Math.sqrt(distX * distX + distY * distY);

        if (delta >= distance) {
            srcX = destX;
            srcY = destY;
            velocity = 0;
            return;
        }

        double dirX = distX / distance;
        double dirY = distY / distance;

        srcX += dirX * delta;
        srcY += dirY * delta;
    }

    private double calculateMovementDelta() {
        return velocity + 0.5 * ACCELERATION;
    }
}
