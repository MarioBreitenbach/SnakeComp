package control.samples.comm;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import model.Direction;
import model.FieldType;
import model.Position;

public class Util {

	public static List<Position> surrounding(int xPos, int yPos) {
		return Arrays.asList(
				new Position(xPos, yPos + 1),
				new Position(xPos + 1, yPos),
				new Position(xPos, yPos - 1),
				new Position(xPos - 1, yPos));
	}

	public static Map<Direction, Position> surroundingMap(int xPos, int yPos) {
		EnumMap<Direction, Position> result = new EnumMap<>(Direction.class);

		result.put(Direction.UP, new Position(xPos, yPos + 1));
		result.put(Direction.LEFT, new Position(xPos + 1, yPos));
		result.put(Direction.DOWN, new Position(xPos, yPos - 1));
		result.put(Direction.RIGHT, new Position(xPos - 1, yPos));

		return result;
	}

	public static int mod(int a, int b) {
		// a %= b;
		// if (a < 0)
		// return a + b;
		// return a;
		return (a %= b) < 0 ? a + b : a;
	}

	private static final Direction[] DIRS = Direction.values();

	public static Direction invert(Direction direction) {
		assert direction != null;
		return DIRS[(direction.ordinal() + 2) % 4];
	}

	public static boolean isNonCollidingSnakeHead(FieldType type) {
		assert type != null;
		switch (type) {
		case SNAKE_HEAD:
		case SNAKE_HEAD_EATING:
			return true;
		default:
			return false;
		}
	}

	public static boolean isSnakeHead(FieldType type) {
		assert type != null;
		switch (type) {
		case COLLISION_ON_FOOD:
		case COLLISION_ON_FREE:
		case COLLISION_ON_WALL:
		case SNAKE_BODY:
		case SNAKE_HEAD:
		case SNAKE_HEAD_EATING:
			return true;
		default:
			return false;
		}
	}

	public static boolean isNonCollidingSnake(FieldType type) {
		assert type != null;
		switch (type) {
		case SNAKE_BODY:
		case SNAKE_HEAD:
		case SNAKE_HEAD_EATING:
			return true;
		default:
			return false;
		}
	}

	public static boolean isCollidingHead(FieldType type) {
		assert type != null;
		switch (type) {
		case COLLISION_ON_FOOD:
		case COLLISION_ON_FREE:
		case COLLISION_ON_WALL:
			return true;
		default:
			return false;
		}
	}

	@Deprecated
	public static Direction adjacentToDirection(int fromX, int fromY, int toX, int toY) {
		switch (toX - fromX) {
		case -1:
			assert fromY - toY == 0;
			return Direction.LEFT;
		case 1:
			assert fromY - toY == 0;
			return Direction.RIGHT;
		case 0:

			switch (toY - fromY) {
			case -1:
				return Direction.DOWN;
			case 1:
				return Direction.UP;
			}
		default:
			assert false;
		}

		return null;
	}

	public static boolean[][] clone(boolean[][] array) {
		return Arrays.stream(array)
				.map(boolean[]::clone)
				.toArray(boolean[][]::new);
	}

	public static boolean isRectangular(boolean[][] array) {
		int length = array.length;
		return Arrays.stream(array)
				.mapToInt(a -> a.length)
				.allMatch(i -> i == length);
	}
}
