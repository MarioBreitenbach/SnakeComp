package model;

import java.io.*;
import java.util.*;

/**
 * Settings for a snake competition.
 * @author cryingshadow
 */
public class Settings extends ChangeListenable {

    /**
     * The maximum field size in pixels.
     */
    public static final int MAX_FIELD_SIZE = 250;

    /**
     * The maximum number of fields in a dimension.
     */
    public static final int MAXIMUM_DIMENSION = 1000;

    /**
     * The minimum field size in pixels.
     */
    public static final int MIN_FIELD_SIZE = 10;

    /**
     * The minimum number of fields in a dimension.
     */
    public static final int MINIMUM_DIMENSION = 5;

    /**
     * The normal field size in pixels.
     */
    public static final int NORMAL_FIELD_SIZE = 50;

    /**
     * Is the maze to be an arena (i.e., having walls at the border of the maze)?
     */
    private boolean arena;

    /**
     * How many pieces of food will be in the maze per participating snake?
     */
    private int foodPerSnake;

    /**
     * The height of the maze.
     */
    private int height;

    /**
     * The initial length of a snake.
     */
    private int initialSnakeLength;

    /**
     * The maximum hunger a snake can survive.
     */
    private Optional<Integer> maxHunger;

    /**
     * Do snakes respawn after they die?
     */
    private boolean respawning;

    /**
     * The directory containing the source files for the snake controls.
     */
    private Optional<File> sourceDirectory;

    /**
     * The speed of the competition execution.
     */
    private Speed speed;

    /**
     * The number of walls in the maze (without the arena walls if set).
     */
    private int walls;

    /**
     * The width of the maze.
     */
    private int width;

    /**
     * The size of a single field.
     */
    private int zoom;

    /**
     * Default settings.
     */
    public Settings() {
        this.zoom = Settings.NORMAL_FIELD_SIZE;
        this.foodPerSnake = 1;
        this.arena = false;
        this.initialSnakeLength = 3;
        this.maxHunger = Optional.of(400);
        this.height = 20;
        this.width = 20;
        this.walls = 50;
        this.sourceDirectory = Optional.empty();
        this.speed = Speed.NORMAL;
        this.respawning = false;
    }

    /**
     * @return How many pieces of food will be in the maze per participating snake?
     */
    public int getFoodPerSnake() {
        return this.foodPerSnake;
    }

    /**
     * @return The height of the maze.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return The initial length of a snake.
     */
    public int getInitialSnakeLength() {
        return this.initialSnakeLength;
    }

    /**
     * @return The maximum hunger a snake can survive.
     */
    public Optional<Integer> getMaxHunger() {
        return this.maxHunger;
    }

    /**
     * @return The directory containing the source files for the snake controls.
     */
    public Optional<File> getSourceDirectory() {
        return this.sourceDirectory;
    }

    /**
     * @return The speed of the competition execution.
     */
    public Speed getSpeed() {
        return this.speed;
    }

    /**
     * @return The number of walls in the maze (without the arena walls if set).
     */
    public int getWalls() {
        return this.walls;
    }

    /**
     * @return The width of the maze.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return The size of a single field.
     */
    public int getZoom() {
        return this.zoom;
    }

    /**
     * @return Is the maze to be an arena (i.e., having walls at the border of the maze)?
     */
    public boolean isArena() {
        return this.arena;
    }

    /**
     * @return Do snakes respawn after they die?
     */
    public boolean isRespawning() {
        return this.respawning;
    }

    /**
     * @param arena Is the maze to be an arena (i.e., having walls at the border of the maze)?
     */
    public void setArena(final boolean arena) {
        this.arena = arena;
        this.notifyChangeListeners();
    }

    /**
     * @param foodPerSnake How many pieces of food will be in the maze per participating snake?
     */
    public void setFoodPerSnake(final int foodPerSnake) {
        this.foodPerSnake = foodPerSnake;
        this.notifyChangeListeners();
    }

    /**
     * @param height The height of the maze.
     */
    public void setHeight(final int height) {
        this.height = height;
        this.notifyChangeListeners();
    }

    /**
     * @param initialSnakeLength The initial length of a snake.
     */
    public void setInitialSnakeLength(final int initialSnakeLength) {
        this.initialSnakeLength = initialSnakeLength;
        this.notifyChangeListeners();
    }

    /**
     * @param maxHunger The maximum hunger a snake can survive.
     */
    public void setMaxHunger(final Optional<Integer> maxHunger) {
        this.maxHunger = maxHunger;
        this.notifyChangeListeners();
    }

    /**
     * @param respawning Do snakes respawn after they die?
     */
    public void setRespawning(final boolean respawning) {
        this.respawning = respawning;
        this.notifyChangeListeners();
    }

    /**
     * @param sourceDirectory The directory containing the source files for the snake controls.
     */
    public void setSourceDirectory(final Optional<File> sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.notifyChangeListeners();
    }

    /**
     * @param speed The speed of the competition execution.
     */
    public void setSpeed(final Speed speed) {
        this.speed = speed;
        this.notifyChangeListeners();
    }

    /**
     * @param walls The number of walls in the maze (without the arena walls if set).
     */
    public void setWalls(final int walls) {
        this.walls = walls;
        this.notifyChangeListeners();
    }

    /**
     * @param width The width of the maze.
     */
    public void setWidth(final int width) {
        this.width = width;
        this.notifyChangeListeners();
    }

    /**
     * @param zoom The size of a single field.
     */
    public void setZoom(final int zoom) {
        this.zoom = zoom;
        this.notifyChangeListeners();
    }

}

