package control.samples.lcss;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import control.samples.comm.Util;
import model.Direction;

public class BField {

	private final int x;
	private final int y;
	private final BMaze maze;

	BField(BMaze maze, int x, int y) {
		this.x = Util.mod(x, maze.width());
		this.y = Util.mod(y, maze.height());
		this.maze = maze;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BMaze getMaze() {
		return maze;
	}

	public boolean isWall() {
		return maze.isWall(x, y);
	}

	public boolean isSnake() {
		return maze.isSnake(x, y);
	}

	public boolean isFood() {
		return maze.isFood(x, y);
	}

	public boolean isObstacle() {
		return maze.isObstacle(x, y);
	}

	public boolean isSnakeCollision() {
		return maze.isSnakeCollision(x, y);
	}

	public BField to(Direction direction) {
		switch (direction) {
		case UP:
			return getMaze().getField(getX(), getY() + 1);
		case RIGHT:
			return getMaze().getField(getX() + 1, getY());
		case DOWN:
			return getMaze().getField(getX(), getY() - 1);
		case LEFT:
			return getMaze().getField(getX() - 1, getY());
		}
		assert false;
		return null;
	}

	public Direction directionTo(BField other) {
		BMaze maze = getMaze();

		if (!maze.equals(other.getMaze()))
			throw new IllegalArgumentException();

		int xDif = Util.mod(other.getX() - getX(), getMaze().width());
		int yDif = Util.mod(other.getY() - getY(), getMaze().height());

		if (xDif > 1)
			xDif -= maze.width();
		if (yDif > 1)
			yDif -= maze.height();

		if (xDif * yDif != 0)
			throw new IllegalArgumentException();

		if (Math.abs(xDif + yDif) != 1)
			throw new IllegalArgumentException();

		switch (xDif) {
		case -1:
			return Direction.LEFT;
		case 1:
			return Direction.RIGHT;
		case 0:
			switch (yDif) {
			case -1:
				return Direction.DOWN;
			case 1:
				return Direction.UP;
			}
		}

		assert false;
		return null;
	}

	public boolean isAdjacentTo(BField other) {
		BMaze maze = getMaze();

		if (!maze.equals(other.getMaze()))
			throw new IllegalArgumentException();

		int xDif = Util.mod(other.getX() - getX(), getMaze().width());
		int yDif = Util.mod(other.getY() - getY(), getMaze().height());

		if (xDif > 1)
			xDif -= maze.width();
		if (yDif > 1)
			yDif -= maze.height();

		return xDif * yDif == 0 && Math.abs(xDif + yDif) == 1;
	}

	public Set<BField> adjacentFields() {
		return new HashSet<>(Arrays.asList(
				to(Direction.UP),
				to(Direction.RIGHT),
				to(Direction.DOWN),
				to(Direction.LEFT)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((maze == null) ? 0 : maze.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		BField other = (BField) obj;
		return Objects.equals(x, other.x) &&
				Objects.equals(y, other.y) &&
				Objects.equals(maze, other.maze);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}

}
