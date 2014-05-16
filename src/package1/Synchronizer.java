package package1;
import java.io.*;

/**
 * Created by Tariq
 * Class Synchronizer
 */

public class Synchronizer {


    // ATTRIBUTS

    /**
     * Width of the area
     */
    public static int gameAreaWidth;

    /**
     * Height of the area
     */
    public static int gameAreaHeight;

    /**
     * Array 2D which represents the area of the game
     */
    private static char[][] gameWorld;

    /**
     * The last key the player pressed
     */
    private static Command[] lastButtonPressed = { new Direction(Direction.RIGHT)};

    /**
     * If the game is over
     */
    private static boolean gameOver = false;


    /**
     * If the snake is alive
     */
    private static boolean snakeAlive;

    /**
     * If the player want or not restart the game
     */
    private static boolean stopGameLoop;

    private static boolean gameLoopRunning;

    private static Food [] food;
    private static boolean [] foodPresent;

    /**
     * array for scores*
     */
    private static int[] scores;
    
    public static int highScore = 0;

    /**
     * maximum number of players *
     */
    public final static int MAX_PLAYER_NUMBER = 2;
    public static int FOOD_NUMBER = 3;
    public static boolean TRON = false;

    public final static int LENGTH = 20;


    // CONSTRUCTOR

    /**
     * Empty constructor
     */
   /* public Synchronizer() {
        gameWorld = new char[LENGTH][LENGTH];
        gameAreaHeight = LENGTH;
        gameAreaWidth = LENGTH;
        gameOver = false;
        stopGameLoop = false;
        gameLoopRunning = false;
        snakeAlive = true;
        scores = new int[MAX_PLAYER_NUMBER];
        scores[0] = 0;
        foodPresent = new boolean[FOOD_NUMBER];
    }*/

    /**
     * 
     */
    /*public Synchronizer(int length) {
        if (length < 10)
            length = LENGTH;
        gameWorld = new char[length][length];
        gameAreaHeight = length;
        gameAreaWidth = length;
        gameOver = false;
        stopGameLoop = false;
        snakeAlive = true;
    }*/


    // METHODS

    public static void setup(){
        gameWorld = new char[LENGTH][LENGTH];
        gameAreaHeight = LENGTH;
        gameAreaWidth = LENGTH;
        gameOver = false;
        stopGameLoop = false;
        gameLoopRunning = false;
        snakeAlive = true;
        scores = new int[MAX_PLAYER_NUMBER];
        scores[0] = 0;
        foodPresent = new boolean[FOOD_NUMBER];
    }

    

    /**
     * Method getGameWorld
     *
     * @return gameWorld
     * the array of the area
     */
    public static char[][] getGameWorld() {
        return gameWorld;
    }


    /**
     * Method setGameWorld
     * which changes the gameWorld used
     *
     * @param gW
     */
    public  static void setGameWorld(char[][] gW) {
        gameWorld = gW;
    }

    /**
     * Method writeThisCell
     * write the char in a cell with given coordinates
     *
     * @param x, y, c
     */
    public  static void writeThisCell(int x, int y, char c) {
        gameWorld[x][y] = c;
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
    public  static int getGameAreaWidth() {
        return gameAreaWidth;
    }


    /**
     * Method setGameAreaWidth
     * which changes the width of the area
     *
     * @param gameAreaWidth
     */
    public  static void setGameAreaWidth(int gameAreaWidth) {
        gameAreaWidth = gameAreaWidth;
    }


    /**
     * Method getGameAreaHeight
     *
     * @return gameAreaHeight
     * the height of the area
     */
    public  static int getGameAreaHeight() {
        return gameAreaHeight;
    }


    /**
     * Method setGameAreaHeight
     * which changes the height of the area
     *
     * @param AreaHeight
     */
    public  static void setGameAreaHeight(int AreaHeight) {
        gameAreaHeight = AreaHeight;
    }


    /**
     * Method isGameOver
     *
     * @return gameOver
     * true only if the game is over
     */
    public  static boolean isGameOver() {
        return gameOver;
    }


    /**
     *
     */
    public  static void theGameIsOver() {
        gameOver = true;
        snakeAlive = false;
    }


    /**
     * @return
     */
    public  static boolean getSnakeStillAlive() {
        return snakeAlive;
    }

    public  static void setSnakeStillAlive(boolean alive) {
        snakeAlive = alive;
    }


    /**
     * Method isStopGameLoop
     *
     * @return stopGameLoop
     * true only if the player doesn't want to restart
     */
    public  static boolean isStopGameLoop() {
        return stopGameLoop;
    }


    /**
     * Method setStopGameLoop
     * changes the situation of the game loop
     * if the game must restart or not
     *
     * @param
     */
    public  static void setStopGameLoop(boolean stop) {
        stopGameLoop = stop;
    }


    /**
     * Method isEmpty
     * return a boolean
     *
     * @param x
     * @param y
     * @return true if the square [x][y] of gameWorld is empty
     */
    public  static boolean isEmpty(int x, int y) {
        return (gameWorld[x][y] == '\0');
    }


    public  static boolean isFood(int x, int y) {
        return (gameWorld[x][y] == Symbol.MOUSE);
    }


    /**
     * Method getScores
     *
     * @return scores
     * returns the array containing the scores
     */
    public  static int[] getScores() {
        return scores;
    }


    /**
     * Method setScores
     * changes the scores array
     * use this to upgrade the score situation
     */
    public  static void setScores(int[] scores) {
        scores = scores;
    }

    public  static void increaseScore(int snakeNumber){
        scores[snakeNumber-1]++;
    }

    public  static boolean isGameLoopRunning() {
        return gameLoopRunning;
    }

    public  static void setGameLoopRunning(boolean gameLoopRunning) {
        gameLoopRunning = gameLoopRunning;
    }

     public  static boolean isFoodPresent(int index) {
        return foodPresent[index];
    }

    public  static void setFoodPresent(boolean present,int index) {
        foodPresent[index] = present;
    }
    
    public  static void resetGame(){
        gameWorld = new char[LENGTH][LENGTH];
        gameAreaHeight = LENGTH;
        gameAreaWidth = LENGTH;
        gameOver = false;
        stopGameLoop = false;
        gameLoopRunning = false;
        snakeAlive = true;
        scores = new int[2];
        scores[0] = 0;
        foodPresent = new boolean[FOOD_NUMBER];
        lastButtonPressed[0].setValue(Direction.RIGHT);
    }
    
    //
    public  static void updateHighScore() {
    	for (int i = 0; i < scores.length; i++){
    		if (scores[i] > highScore)
    			highScore = scores[i];
    	}
    }
    
    
    public  static void saveHighScore(String fileName ) throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter (fileName, true));
		updateHighScore();
		pw.printf("%d", highScore);
		pw.close();
	}
    
    public  static void initializeHighScore(String fileName) throws IOException {
    	try {
    		
    		FileReader fr = new FileReader (fileName);
    		int res = fr.read();
    		highScore = Character.getNumericValue((res));
    		fr.close();
    	}
    	catch (IOException e){
    		highScore = 0;
    	}
    }
    
    
    /*public  static void setFood (Food [] food)  {
        food = food;
    }*/
    
    
    public static Food [] getFood () {
    	return food;
    }

    public static  void setFood (Food[] foodArray) {
        food = foodArray;
    }

     public static void setHighScore(int highS){
    	if (highS < 0)
    		highS = 0;
    	highScore = highS;
    }
    
}

