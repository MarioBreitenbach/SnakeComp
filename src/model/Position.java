package model;

/**
 * A simple 2D position.
 * @author cryingshadow
 */
public class Position {

    /**
     * The position on the x-axis.
     */
    private final int x;

    /**
     * The position on the y-axis.
     */
    private final int y;

    /**
     * @param x The position on the x-axis.
     * @param y The position on the y-axis.
     */
    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param target A surrounding target position.
     * @return The direction in which the target position is from this position.
     */
    public Direction computeDirection(final Position target) {
        final int xDist = target.getX() - this.getX();
        final int yDist = target.getY() - this.getY();
        if (xDist == 0) {
            if (yDist == 1 || yDist < -1) {
                return Direction.UP;
            }
            return Direction.DOWN;
        } else if (xDist == 1 || xDist < -1) {
            return Direction.RIGHT;
        }
        return Direction.LEFT;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Position) {
            final Position p = (Position)o;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    /**
     * @return The position on the x-axis.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return The position on the y-axis.
     */
    public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        return 3 * this.x + 5 * this.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
