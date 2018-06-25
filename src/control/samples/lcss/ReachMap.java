package control.samples.lcss;

import java.util.Deque;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;

import model.Direction;

public class ReachMap {

	private final int[][] map;
	private final BField target;

	public ReachMap(BField centerField) {

		BMaze maze = centerField.getMaze();
		map = new int[maze.width()][maze.height()];

		Deque<BField> queue = new LinkedList<>();

		map[centerField.getX()][centerField.getY()] = 1;
		queue.add(centerField);

		while (!queue.isEmpty()) {
			assert !centerField.isWall();
			BField curr = queue.pop();

			int i = get(curr) + 1;
			curr.adjacentFields().stream()
					.filter(adj -> get(adj) == 0)
					.forEach(adj -> {
						if (adj.isWall())
							map[adj.getX()][adj.getY()] = -1;
						else {
							map[adj.getX()][adj.getY()] = i;
							queue.add(adj);
						}
					});
		}

		this.target = centerField;

		// System.out.println(Arrays.deepToString(map).replace("], [", "\n"));
	}

	public int get(int x, int y) {
		return map[x][y];
	}

	public int get(BField field) {
		return get(field.getX(), field.getY());
	}

	public Set<Direction> toCloser(BField field) {
		int dist = get(field);

		Set<Direction> res = EnumSet.noneOf(Direction.class);

		for (Direction d : Direction.values()) {
			int adjDist = get(field.to(d));

			if (adjDist != -1 && adjDist < dist)
				res.add(d);
		}

		return res;
	}

	public BField target() {
		return target;
	}
}
