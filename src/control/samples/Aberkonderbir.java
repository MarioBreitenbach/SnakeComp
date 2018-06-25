/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.samples;

import control.SnakeControl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Direction;
import model.Maze;
import model.Position;
import model.Walls;

/**
 *
 * @author sid93_000
 */
public class Aberkonderbir implements SnakeControl {

	private Position pos;
	private ArrayList<Position> nextdirection;
	private int temp1,temp2,temp3;
	private int currentPosdistance;
	private int oldPosdistance=1000000000;
	private Position food;

	enum direction{
		RIGHT,
		LEFT,
		UP,
		DOWN;
	}

	direction fastesway;

	@Override
	public String getName() {

		return "Aberkondebir";

	}

	@Override
	public Direction nextDirection(Maze maze, int xPos, int yPos) {

        /*    if(isWall(maze,xPos,yPos+1)==true){
                System.out.println("Abernondebir auf dem Weg nach oben");
                return Direction.UP;

            }else if(isWall(maze,xPos,yPos-1)==true){
                System.out.println("Abernondebir auf dem Weg nach unten");
                return Direction.DOWN;

            }else if(isWall(maze,xPos-1,yPos)==true){
                System.out.println("Abernondebir auf dem Weg nach left");
                return Direction.LEFT;

            }else if(isWall(maze,xPos+1,yPos)==true){
                System.out.println("Abernondebir auf dem Weg nach recht");
                return Direction.RIGHT;

            }
        */
		fastesWay(maze,xPos,yPos);
//        this.nextDecision(maze, xPos, yPos);
		boolean wayfoundet =false;
		while(wayfoundet==false){
			switch (fastesway){
				case LEFT:
					if(isWall(maze,xPos-1,yPos)==true){
						return Direction.LEFT;

					}else{
						fastesway= direction.RIGHT;
						continue;
					}
				case RIGHT:
					if(isWall(maze,xPos+1,yPos)==true){
						return Direction.RIGHT;
					}else{
						fastesway=direction.UP;
						continue;
					}

				case UP:
					if(isWall(maze,xPos,yPos+1)==true){
						return Direction.UP;
					}else{
						fastesway= direction.DOWN;
						continue;
					}

				case DOWN:
					if(isWall(maze,xPos,yPos-1)==true){
						return Direction.DOWN;

					}else{
						this.fastesway= direction.RIGHT;
						continue;
					}

			}
			wayfoundet=true;
		}
		return Direction.RIGHT;
	}

	public boolean isWall_up_right(Maze maze,int xPos, int yPos){

		Position nextPos= new Position(xPos,yPos);

		if(maze.getFreePositions().contains(nextPos)==true){
			return true;
		}else if(nextFood(maze,xPos,yPos)==true){
			return true;
		}
		return false;
	}

	public boolean isWall(Maze maze,int xPos,int yPos){
		if(xPos<0){
			xPos=maze.getWidth()+xPos;
		}else if(yPos<0){
			yPos= maze.getHeight()+yPos;
		}
		this.pos= new Position(xPos%maze.getWidth(),yPos%maze.getHeight());

		if(maze.getFreePositions().contains(pos)==true){
			return true;
		}else if(nextFood(maze,xPos,yPos)==true){
			return true;
		}
		return false;
	}

	public boolean nextFood(Maze maze,int xPos, int yPos){
		this.pos= new Position(xPos,yPos);
		if(maze.getFood().contains(pos)){
			return true;
		}
		return false;
	}



	//    public void findClosestFood(Maze maze, int xPos, int yPos){
//        final List<Position> food = new ArrayList<Position>(maze.getFood());
//        for(int i=0;i<maze.getFood().size();i++){
//        {
//            currentPosdistance=0;
//            food.get(i);
//            temp1=food.get(i).getX()-xPos;
//            temp2=food.get(i).getY()-yPos;
//            if(temp1<food.get(i).getX()/2){
//                for(int j =0;xPos==food.get(i).getX();j++){
//                    xPos--;
//                    if(xPos>maze.getWidth()){
//                        xPos=1;
//                    }
//                    this.currentPosdistance++;
//                }
//            }else{
//                for(int j =0;xPos==food.get(i).getX();j++){
//                    xPos--;
//                    if(xPos<1){
//                        xPos=maze.getWidth();
//                    }
//                    this.currentPosdistance++;
//                }
//            }
//        }
//                if(temp2<food.get(i).getY()/2)
//                {
//                    for(int j =0;yPos==food.get(i).getX();j++){
//                        yPos++;
//                        if(yPos>maze.getHeight()){
//                            yPos=1;
//                        }
//                        this.currentPosdistance++;
//                    }
//                }else{
//                    for(int j =0;yPos==food.get(i).getX();j++){
//                        yPos--;
//                        if(yPos<1){
//                            xPos=maze.getHeight();
//                        }
//                        this.currentPosdistance++;
//                    }
//                }
//            if(this.oldPosdistance>currentPosdistance){
//               oldPosdistance=currentPosdistance;
//               System.out.print(oldPosdistance);
//               this.food=food.get(i);
//            }
//        }
//    }
//
	public void fastesWay(Maze maze,int xPos,int yPos){


		List <Position> food = new ArrayList(maze.getFood());


		if(xPos!=food.get(0).getX()){
			if(xPos<food.get(0).getX()){
				fastesway = direction.LEFT;
				//System.out.print("links");
			}else{
				fastesway= direction.RIGHT;
				//System.out.print("rechts");
			}
		}else if(yPos!=food.get(0).getY()){
			if(yPos<food.get(0).getY()){
				fastesway = direction.UP;
				//System.out.print("oben");
			}else{
				fastesway= direction.DOWN;
				//System.out.print("unten");
			}
		}



	}

	public void nextDecision(Maze maze,int xPos,int yPos){

		boolean faster=false;

		if(xPos==food.getX()){
			faster=true;
		}
		int counter = 0;
		int counter2 =0;
		List <Position> food = new ArrayList(maze.getFood());
		while(xPos!=food.get(0).getX()){
			xPos++;
			if(xPos>maze.getWidth()){
				xPos=0;
			}
			counter++;
		}
		while(xPos!=food.get(0).getX()){
			xPos++;
			if(xPos>maze.getWidth()){
				xPos=0;
			}
			counter++;
		}
		while(xPos!=food.get(0).getX()){
			xPos--;
			if(xPos<0){
				xPos=maze.getWidth();
			}
			counter2++;
		}

		if(xPos!=food.get(0).getX()){
			if(counter<counter2){
				this.fastesway=direction.LEFT;
			}else{
				this.fastesway=direction.RIGHT;
			}
		}

		while(yPos!=food.get(0).getY()){
			yPos++;
			if(yPos>maze.getHeight()){
				yPos=0;
			}
			counter++;
		}
		while(yPos!=food.get(0).getY()){
			yPos++;
			if(yPos>maze.getHeight()){
				yPos=0;
			}
			counter++;
		}
		while(yPos!=food.get(0).getY()){
			yPos--;
			if(yPos<0){
				yPos=maze.getHeight();
			}
			counter2++;
		}

		if(faster==true){
			if(counter<counter2){
				this.fastesway=direction.LEFT;
			}else{
				this.fastesway=direction.DOWN;
			}
		}



	}


}
