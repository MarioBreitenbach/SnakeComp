package control.samples;

import java.util.*;
import java.util.stream.Collectors;

import control.SnakeControl;
import model.Direction;
import model.Maze;
import model.Position;

/**
 * A randomly moving snake.
 *
 * @author cryingshadow
 */
public class MasterSnakeControl implements SnakeControl {

	/**
	 * @param p1
	 *            Some position.
	 * @param p2
	 *            Some other position.
	 * @return The Manhattan distance between the specified positions.
	 */
	private final Random random = new Random();

	public static int manhattanDistance(final Position p1, final Position p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}

	@Override
	public String getName() {
		return "Master";
	}

	@Override
	public Direction nextDirection(final Maze maze, final int xPos, final int yPos) {
		final List<Position> food = new ArrayList<Position>(maze.getFood());
		final Position curPos = new Position(xPos, yPos);
		food.sort(new Comparator<Position>() {

			@Override
			public int compare(final Position o1, final Position o2) {
				return MasterSnakeControl.manhattanDistance(o1, curPos)
						- MasterSnakeControl.manhattanDistance(o2, curPos);
			}
		});

		final List<Position> surrounding = maze.getSurroundingPositions(xPos, yPos).stream()
				.filter(pos -> !maze.getField(pos).getType().isObstacle()).collect(Collectors.toList());

		final Position foodPos = food.get(0);
		final int foodX = foodPos.getX();
		final int foodY = foodPos.getY();
		//System.out.println("kkk "+foodX+"  "+foodY+"  "+xPos+"  "+yPos);
		if (foodX > xPos && (surrounding.isEmpty())) {
			return Direction.RIGHT;
		}
		if (foodX < xPos && (surrounding.isEmpty())) {
			return Direction.LEFT;
		}
		if (foodY > yPos && (surrounding.isEmpty())) {
			return Direction.UP;
		}
		if ((surrounding.isEmpty())) {
			return Direction.DOWN;
		}
		return new Position(xPos, yPos).computeDirection(surrounding.get(this.random.nextInt(surrounding.size())));

	}

}
