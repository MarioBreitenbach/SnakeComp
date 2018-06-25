package control.samples.lcss;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.Maze;

public class BMaze {

	private Maze rawMaze;
	private Map<BField, ReachMap> foodMaps;

	public BMaze(Maze rawMaze) {
		this.rawMaze = rawMaze;

		foodMaps = rawMaze.getFood().stream()
				.map(p -> getField(p.getX(), p.getY()))
				.collect(Collectors.toMap(Function.identity(), ReachMap::new));
	}

	public int width() {
		return rawMaze.getWidth();
	}

	public int height() {
		return rawMaze.getHeight();
	}

	public BField getField(int x, int y) {
		return new BField(this, x, y);
	}

	public boolean isWall(int x, int y) {
		switch (rawMaze.getField(x, y).getType()) {
		case COLLISION_ON_WALL:
		case WALL:
			return true;
		default:
			return false;
		}
	}

	public boolean isSnake(int x, int y) {
		switch (rawMaze.getField(x, y).getType()) {
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

	public boolean isFood(int x, int y) {
		switch (rawMaze.getField(x, y).getType()) {
		case COLLISION_ON_FOOD:
		case FOOD:
		case SNAKE_HEAD_EATING:
			return true;
		default:
			return false;
		}
	}

	public Map<BField, ReachMap> getFoodReachMaps() {
		return Collections.unmodifiableMap(foodMaps);
	}

	public boolean isObstacle(int x, int y) {
		return isWall(x, y) || isSnake(x, y);
	}

	public boolean isSnakeCollision(int x, int y) {
		switch (rawMaze.getField(x, y).getType()) {
		case COLLISION_ON_FOOD:
		case COLLISION_ON_FREE:
		case COLLISION_ON_WALL:
			return true;
		default:
			return false;
		}
	}

	public void setRawMaze(Maze rawMaze) {
		this.rawMaze = rawMaze;

		Set<BField> newFood = rawMaze.getFood().stream()
				.map(p -> getField(p.getX(), p.getY()))
				.collect(Collectors.toSet());

		foodMaps.keySet().removeIf(f -> !newFood.remove(f));

		newFood.forEach(f -> foodMaps.put(f, new ReachMap(f)));
	}

	public Set<ReachMap> mapsToClosestFoods(BField field) {

		int[] min = { Integer.MAX_VALUE };
		Set<ReachMap> result = new HashSet<>();

		foodMaps.values().forEach(m -> {
			int dist = m.get(field);

			if (dist == -1 || dist > min[0])
				return;

			if (dist < min[0])
				result.clear();

			min[0] = dist;
			result.add(m);
		});

		return result;
	}
}
