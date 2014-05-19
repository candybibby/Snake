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
    private Snake snake;

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
    public World()
    {

        this.snake = new Snake();
        this.food = new Food [Synchronizer.FOOD_NUMBER];
        for (int i=0; i< Synchronizer.FOOD_NUMBER; i++) 
            food[i] = new Food();
        Synchronizer.setFood(this.food);
        this.obstacle = new Obstacle();
        this.gameEngine = new GameEngine(this.snake, 5, this.food);
    }

    //METHODS

    /**
     * Method createNewGame
     * Create a new world in which places
     * the Obstacles, the Snake and the Food each time in the game area
     *
     * @param lvl is the level chosen for the game
     */
    public void createNewGame(int lvl){
    	try{
    		Synchronizer.initializeHighScore("highScore.txt");
    		this.obstacle = new Obstacle(lvl);    //create new obstacle placed in the area
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
	public Snake getSnake()  
	{
		return this.snake;
	}
	//Setter for the new snake that is created
	public void setNewSnake(Snake s) 
	{
		this.snake = s;
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

