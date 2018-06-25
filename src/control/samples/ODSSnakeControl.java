package control.samples;

import control.*;
import model.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import java.util.*;
import java.util.stream.Collectors;

public class ODSSnakeControl implements SnakeControl
{
	private final Random random = new Random();

	@Override
	public String getName()
	{
		return "ODSSnake";
	}

	@Override
	public Direction nextDirection(Maze maze, int xPos, int yPos)
	{
		final List<Position> surrounding =
				maze
						.getSurroundingPositions(xPos, yPos)
						.stream()
						.filter(pos -> !maze.getField(pos).getType().isObstacle())
						.collect(Collectors.toList());
		if (surrounding.isEmpty())
		{
			return Direction.UP;
		}
		final List<Position> food = new ArrayList<Position>(maze.getFood());
		final Position curPos = new Position(xPos, yPos);

		food.sort(
				new Comparator<Position>()
				{

					@Override
					public int compare(final Position o1, final Position o2)
					{
						return
								GreedySnakeControl.manhattanDistance(o1, curPos)
										- GreedySnakeControl.manhattanDistance(o2, curPos);
					}

				}
		);
		final List<Direction> surroundingDirection = new ArrayList<>();
		for (Position p: surrounding)
		{
			surroundingDirection.add(new  Position(xPos,yPos).computeDirection(p));
		}

		final Position foodPos = food.get(0);
		final int foodX = foodPos.getX();
		final int foodY = foodPos.getY();
		if (foodX > xPos)
		{
			for (Direction d: surroundingDirection)
			{
				if (d == Direction.RIGHT)
				{
					return Direction.RIGHT;
				} else
				{
					return surroundingDirection.get(surrounding.size()-1);
				}
			}
		}
		if (foodX < xPos)
		{
			for (Direction d: surroundingDirection)
			{
				if (d == Direction.LEFT)
				{
					return Direction.LEFT;
				} else
				{
					return surroundingDirection.get(surrounding.size()-1);
				}
			}
		}
		if (foodY > yPos)
		{
			for (Direction d: surroundingDirection)
			{
				if (d == Direction.UP)
				{
					return Direction.UP;
				} else
				{
					return surroundingDirection.get(surrounding.size()-1);
				}
			}
		}
		for (Direction d: surroundingDirection)
		{
			if (d == Direction.DOWN)
			{
				return Direction.DOWN;
			} else
			{
				return surroundingDirection.get(surrounding.size()-1);
			}
		}
		return Direction.DOWN;
	}
}
