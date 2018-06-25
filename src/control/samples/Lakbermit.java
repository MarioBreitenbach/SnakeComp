package control.samples;

import model.*;

import java.util.*;

import control.*;

/**
 * dk
 * @author Nigedei
 */
public class Lakbermit implements SnakeControl {


	int lowestDelta;
	ArrayList<Position> routeList = new ArrayList<Position>();
	Position nearestFood;
	ArrayList<Position> food = new ArrayList<Position>();
	Position routePosition;
	boolean routePosIsInit = false;

	/**
	 * @param p1 Some position.
	 * @param p2 Some other position.
	 * @return The Manhattan distance between the specified positions.
	 */


	@Override
	public String getName() {
		return "Lakbermit";
	}

	@Override
	public Direction nextDirection(Maze maze, int xPos, int yPos) {

		lowestDelta=1000;

		if(!routePosIsInit) {
			routePosition=new Position(xPos,yPos);
			routePosIsInit=true;
		}

		food.clear();
		for(Position foodPos: maze.getFood()) {
			food.add(foodPos);
		}

		checkBestFood(maze,xPos,yPos);
		return checkBestFoodDirection(maze,xPos,yPos);
	}

	private Direction checkBestFoodDirection(Maze maze, int xPos, int yPos) {

		Direction bestChoice = Direction.UP;
		int lowestDelta=500;

		int deltaUp = getFoodDistance(xPos,yPos+1);
		int deltaDown = getFoodDistance(xPos,yPos-1);
		int deltaLeft = getFoodDistance(xPos-1,yPos);
		int deltaRight = getFoodDistance(xPos+1,yPos);

		if(checkNextDirection(maze,xPos,yPos,Direction.UP)) {
			lowestDelta=deltaUp;
		}

		if(deltaDown<lowestDelta&&checkNextDirection(maze,xPos,yPos,Direction.DOWN)) {
			lowestDelta=deltaDown;
			bestChoice=Direction.DOWN;
		}

		if(deltaLeft<lowestDelta&&checkNextDirection(maze,xPos,yPos,Direction.LEFT)) {
			lowestDelta=deltaLeft;
			bestChoice=Direction.LEFT;
		}

		if(deltaRight<lowestDelta&&checkNextDirection(maze,xPos,yPos,Direction.RIGHT)) {
			lowestDelta=deltaRight;
			bestChoice=Direction.RIGHT;
		}

		return bestChoice;
	}

	private void checkBestFood(Maze maze, int xPos, int yPos) {

		//System.out.println("checkBestFood startet und lowestDelta ist: "+lowestDelta);
		int lowestX=20;
		int lowestY=20;

		for(int i = 0; i < food.size(); i++) {
			int foodX = food.get(i).getX();
			int foodY = food.get(i).getY();
			//System.out.println("Food-Koordinate: "+foodX+"/"+foodY);

			int deltaX = (Math.abs(xPos-foodX));
			int deltaY = (Math.abs(yPos-foodY));
			int deltaAll = deltaX+deltaY;
			//System.out.println("Delta-Werte: "+deltaX+"/"+deltaY+" Summe: "+deltaAll);

			if(deltaAll<lowestDelta) {
				lowestDelta=deltaAll;
				lowestX=foodX;
				lowestY=foodY;
			}
		}
		nearestFood=new Position(lowestX,lowestY);
		//System.out.println("NearestFood: "+nearestFood);
	}


	private boolean checkNextDirection(Maze maze, int xPos, int yPos, Direction direction) {
		Position nextPos = null;
		switch (direction) {
			case UP:
				nextPos = new Position(xPos,yPos+1);
				break;
			case DOWN:
				nextPos = new Position(xPos, yPos-1);
				break;
			case LEFT:
				nextPos = new Position(xPos-1,yPos);
				break;
			case RIGHT:
				nextPos = new Position(xPos+1,yPos);
				break;
		}

		if(maze.getFreePositions().contains(nextPos)||nextPos.getX()==-1||nextPos.getX()==maze.getWidth()||nextPos.getY()==-1||nextPos.getY()==maze.getHeight() || maze.getFood().contains(nextPos)) {
			return true;
		}else {
			//System.out.println("STOP");

			return false;
		}

	}


	private int getFoodDistance(int xPos, int yPos)
	{
		return Math.round((Math.abs(nearestFood.getX()-xPos))+(Math.abs(nearestFood.getY()-yPos)));
	}

}
