package package1;
import java.io.*;

/**
 * World class
 * UK people
 * 
 */
public class World {

    //ATTRIBUTES
    
    /**
     * Instance variables for the class
     */


    //Snake
    private Snake [] snake;

    //Food
    private Food [] food;

    //Obstacles
    private Obstacle obstacle;
    
    //Game Engine
    public GameEngine gameEngine;




    //CONSTRUCTORS

    /**
     * Constructor
     * Initialize all the attributes by default
     * and create a new World
     */
    public World(int lvl)
    {
        this.snake = new Snake[Synchronizer.getNumberOfPlayer()];
        if (Synchronizer.getNumberOfPlayer() == 2) {
            this.snake[0] = new Snake((int) (Synchronizer.getGameAreaWidth()) / 4, (int) (Synchronizer.getGameAreaHeight()) / 4, 0);
            this.snake[1] = new Snake((int) (Synchronizer.getGameAreaWidth()) / 4 * 3, (int) (Synchronizer.getGameAreaHeight()) / 4 * 3, 1);
        }
        else {
            this.snake[0] = new Snake();

        }
        this.food = new Food [Synchronizer.foodNumber];
        this.obstacle = new Obstacle(lvl);
        for (int i=0; i< Synchronizer.foodNumber; i++)
            food[i] = new Food();
        Synchronizer.setFood(this.food);
        this.gameEngine = new GameEngine(this.snake, 5, this.food);
    }

    //METHODS

    /**
     * Method createNewGame
     * Create a new world in which places
     * the Obstacles, the Snake and the Food each time in the game area
     *
     */
    public void createNewGame(){
    	try{
    		Synchronizer.initializeHighScore("highScore.txt");
    		this.gameEngine.startYourEngines();
    	}
    	catch(IOException e){}
       /*while(snakeAlive){                                             //While the snake is alive, the game continues
		   this.snake.Movet();                                  //Allows the snake to move
		   this.food.placeFood();                               //Places the food while the snake is moving
		   snakeAlive = this.synchronizer.getSnakeStillAlive(); //checks each time is the snake is alive
	   }*/
	  
    }


    //world.setNewSnake(world.getSnake().reset())

        //Getter for the snake
	public Snake getSnake(int snakeId)
	{
		return this.snake[snakeId];
	}
	//Setter for the new snake that is created
	public void setNewSnake(Snake s, int snakeId)
	{
		this.snake[snakeId] = s;
	}
	

	/**
	 * @return the synchronizer
	 */
	/*public Synchronizer getSynchronizer() {
		return this.synchronizer;
	}*/


	/**
	 * @return the food
	 */
	public Food[] getFood() {
		return this.food;
	}


	/**
	 * @return the obstacle
	 */
	public Obstacle getObstacle() {
		return this.obstacle;
	}


	/**
	 * @return the gameEngine
	 */
	public GameEngine getGameEngine() {
		return this.gameEngine;
	}




}

