package package1;
import java.io.*;

/**
 * World class
 * <p/>
 * UK People
 */
public class World {

    //ATTRIBUTES
    
    /**
     * Instance variables for the class
     */


    //Sychronizer
    private Synchronizer synchronizer;

    //Snake
    private Snake snake;

    //Food
    private Food [] food;

    //Obstacles
    private Obstacle obstacle;
    
    //Game Engine
    public GameEngine gameEngine;

    //Display
    private Display display;

    //Control
    private Control control;


    //CONSTRUCTORS

    /**
     * Constructor
     * Initialize all the attributes by default
     * and create a new World
     */
    public World(Synchronizer synch) 
    {
        this.synchronizer = synch;
              this.synchronizer = synch;
        this.snake = new Snake(this.synchronizer);
        this.food = new Food [Synchronizer.FOOD_NUMBER];
        for (int i=0; i< Synchronizer.FOOD_NUMBER; i++) 
            food[i] = new Food(this.synchronizer);
        this.synchronizer.setFood(this.food);
        this.obstacle = new Obstacle(this.synchronizer);
        this.display = new Display(this.synchronizer);
        this.control = new Control(this.synchronizer);
        this.gameEngine = new GameEngine(this.snake, this.display, this.control, 5, this.synchronizer, this.food);
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
    		this.synchronizer.initializeHighScore("highScore.txt");
    		this.obstacle = new Obstacle(lvl, this.synchronizer);    //create new obstacle placed in the area
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

}

