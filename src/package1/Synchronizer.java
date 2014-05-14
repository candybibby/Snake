package package1;

/**
 * Created by Tariq
 * Class Synchronizer
 */

public class Synchronizer {


    // ATTRIBUTS

    /**
     * Width of the area
     */
    public int gameAreaWidth;

    /**
     * Height of the area
     */
    public int gameAreaHeight;

    /**
     * Array 2D which represents the area of the game
     */
    private char[][] gameWorld;

    /**
     * The last key the player pressed
     */
    private static Command[] lastButtonPressed;

    /**
     * If the game is over
     */
    private boolean gameOver = false;


    /**
     * If the snake is alive
     */
    private boolean snakeAlive;

    /**
     * If the player want or not restart the game
     */
    private boolean stopGameLoop;

    private boolean gameLoopRunning;

    private boolean foodPresent;

    /**
     * array for scores*
     */
    private int[] scores;

    /**
     * maximum number of players *
     */
    private int MAX_PLAYER_NUMBER = 2;

    public final static int LENGTH = 20;


    // CONSTRUCTOR

    /**
     * Empty constructor
     */
    public Synchronizer() {
        this.gameWorld = new char[LENGTH][LENGTH];
        this.gameAreaHeight = LENGTH;
        this.gameAreaWidth = LENGTH;
        this.gameOver = false;
        this.stopGameLoop = false;
        this.gameLoopRunning = false;
        this.snakeAlive = true;
        this.scores = new int[2];
        this.scores[0] = 0;
        this.foodPresent = false;
    }

    /**
     * @param length
     */
    public Synchronizer(int length) {
        if (length < 10)
            length = LENGTH;
        this.gameWorld = new char[length][length];
        this.gameAreaHeight = length;
        this.gameAreaWidth = length;
        this.gameOver = false;
        this.stopGameLoop = false;
        this.snakeAlive = true;
    }


    // METHODS


    /**
     * Method initialize
     * which initializes the game with an empty area
     *
     * @param width
     * @param height
     */
    public void initialize(int width, int height) {
        this.gameWorld = new char[width][height];
        this.gameAreaHeight = height;
        this.gameAreaWidth = width;
    }


    /**
     * Method getGameWorld
     *
     * @return gameWorld
     * the array of the area
     */
    public char[][] getGameWorld() {
        return this.gameWorld;
    }


    /**
     * Method setGameWorld
     * which changes the gameWorld used
     *
     * @param gW
     */
    public void setGameWorld(char[][] gW) {
        this.gameWorld = gW;
    }


    /**
     * Method getLastButtonPressed
     *
     * @return lastButtonPressed
     * the last button pressed by the player
     */
    public static Command[] getLastButtonPressed() {
        return lastButtonPressed;
    }


    /**
     * Method setLastButtonPressed
     * which changes the last button pressed by the player by another one
     *
     * @param mlastButtonPressed
     */
    public static void setLastButtonPressed(Command[] mlastButtonPressed) {
        lastButtonPressed = mlastButtonPressed;
    }


    /**
     * Method getGameAreaWidth
     *
     * @return gameAreaWidth
     * the width of the area
     */
    public int getGameAreaWidth() {
        return this.gameAreaWidth;
    }


    /**
     * Method setGameAreaWidth
     * which changes the width of the area
     *
     * @param gameAreaWidth
     */
    public void setGameAreaWidth(int gameAreaWidth) {
        this.gameAreaWidth = gameAreaWidth;
    }


    /**
     * Method getGameAreaHeight
     *
     * @return gameAreaHeight
     * the height of the area
     */
    public int getGameAreaHeight() {
        return this.gameAreaHeight;
    }


    /**
     * Method setGameAreaHeight
     * which changes the height of the area
     *
     * @param gameAreaHeight
     */
    public void setGameAreaHeight(int gameAreaHeight) {
        this.gameAreaHeight = gameAreaHeight;
    }


    /**
     * Method isGameOver
     *
     * @return gameOver
     * true only if the game is over
     */
    public boolean isGameOver() {
        return this.gameOver;
    }


    /**
     *
     */
    public void theGameIsOver() {
        this.gameOver = true;
        this.snakeAlive = false;
    }


    /**
     * @return
     */
    public boolean getSnakeStillAlive() {
        return this.snakeAlive;
    }

    public void setSnakeStillAlive(boolean alive) {
        this.snakeAlive = alive;
    }


    /**
     * Method isStopGameLoop
     *
     * @return stopGameLoop
     * true only if the player doesn't want to restart
     */
    public boolean isStopGameLoop() {
        return this.stopGameLoop;
    }


    /**
     * Method setStopGameLoop
     * changes the situation of the game loop
     * if the game must restart or not
     *
     * @param stopGameLoop
     */
    public void setStopGameLoop(boolean stopGameLoop) {
        this.stopGameLoop = stopGameLoop;
    }


    /**
     * Method isEmpty
     * return a boolean
     *
     * @param x
     * @param y
     * @return true if the square [x][y] of gameWorld is empty
     */
    public boolean isEmpty(int x, int y) {
        return (this.gameWorld[x][y] == '\0');
    }


    public boolean isFood(int x, int y) {
        return (this.gameWorld[x][y] == Symbol.MOUSE);
    }


    /**
     * Method getScores
     *
     * @return scores
     * returns the array containing the scores
     */
    public int[] getScores() {
        return scores;
    }


    /**
     * Method setScores
     * changes the scores array
     * use this to upgrade the score situation
     */
    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public void increaseScore(int snakeNumber){
        this.scores[snakeNumber-1]++;
    }

    public boolean isGameLoopRunning() {
        return gameLoopRunning;
    }

    public void setGameLoopRunning(boolean gameLoopRunning) {
        this.gameLoopRunning = gameLoopRunning;
    }

    public boolean isFoodPresent() {
        return foodPresent;
    }

    public void setFoodPresent(boolean foodPresent) {
        this.foodPresent = foodPresent;
    }
}

