package control.samples;

import control.SnakeControl;
import control.samples.lcss.BField;
import control.samples.lcss.BMaze;
import control.samples.lcss.LCSSControl;
import model.Direction;
import model.Maze;

public class LCSS implements SnakeControl {

	private LCSSControl control;

	@Override
	public String getName() {
		return "LC-Schwing-Snake";
	}

	@Override
	public Direction nextDirection(Maze rawMaze, int xPos, int yPos) {

		if (forceInit(rawMaze, xPos, yPos))
			init(rawMaze, xPos, yPos);
		else
			update(rawMaze, xPos, yPos);

		BField curr = control.getMaze().getField(xPos, yPos);
		BField next = control.nextField(curr);

		//System.out.println("Chose: " + next);

		if (next == null) {
			//System.out.println("G-fuckin'-G");
			return Direction.UP;
		}

		assert curr.isAdjacentTo(next);

		return curr.directionTo(next);
	}

	private void update(Maze rawMaze, int xPos, int yPos) {
		this.control.getMaze().setRawMaze(rawMaze);
	}

	private void init(Maze rawMaze, int xPos, int yPos) {
		this.control = new LCSSControl(new BMaze(rawMaze));
	}

	private boolean forceInit(Maze rawMaze, int xPos, int yPos) {
		return control == null;
	}

}
