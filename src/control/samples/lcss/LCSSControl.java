package control.samples.lcss;

import java.util.Set;
import java.util.stream.Collectors;

public class LCSSControl {

	private final BMaze maze;

	private Set<ReachMap> closestFoods;

	public LCSSControl(BMaze maze) {
		this.maze = maze;
	}

	public BField nextField(BField curr) {
		System.out.println(curr);

		loadClosestFood(curr);

		Set<BField> freeAdj = curr.adjacentFields().stream()
				.filter(f -> !f.isWall() && (!f.isSnake() || f.isSnakeCollision()))
				.collect(Collectors.toSet());

		if (freeAdj.isEmpty()) {
			System.out.println("No free fields around");
			return bestOf(curr.adjacentFields(), curr);
		}

		//System.out.println(freeAdj);

		Set<BField> saveAdj = freeAdj.stream()
				.filter(f -> {
					return f.adjacentFields().stream()
							.filter(adjF -> !adjF.equals(curr))
							.filter(adj -> !adj.isSnakeCollision())
							.noneMatch(adjF -> adjF.isSnake());
				})
				.collect(Collectors.toSet());

		if (saveAdj.isEmpty()) {
			//System.out.println("No save fields around");
			return bestOf(freeAdj, curr);
		}

		//System.out.println("Save fields: " + saveAdj);
		return bestOf(saveAdj, curr);
	}

	private void loadClosestFood(BField curr) {
		closestFoods = maze.mapsToClosestFoods(curr);
	}

	private BField bestOf(Set<BField> regarded, BField curr) {
		assert !regarded.isEmpty();

		Set<BField> bestFields = closestFoods.stream()
				.flatMap(rm -> rm.toCloser(curr).stream())
				.distinct()
				.map(curr::to)
				.collect(Collectors.toSet());

		bestFields.retainAll(regarded);

		if (bestFields.isEmpty())
			return regarded.iterator().next();

		return bestFields.iterator().next();

		// return curr.to(closestFoods.iterator().next().toCloser(curr).iter);

		// return regarded.iterator().next();

		// int bestDist = Integer.MAX_VALUE;
		// BField bestField = regarded.iterator().next();
		//
		// for (BField field : regarded) {
		// Set<ReachMap> maps = maze.mapsToClosestFoods(field);
		//
		// assert !maps.isEmpty();
		//
		// int dist = maps.iterator().next().get(field);
		//
		// if (dist < bestDist)
		// bestField = field;
		// }
		//
		// assert regarded.contains(bestField);

		// return bestField;
	}

	public BMaze getMaze() {
		return maze;
	}

}
