package package1;

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
    private Food food;

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
        this.snake = new Snake(this.synchronizer);
        this.food = new Food(this.synchronizer);
        this.obstacle = new Obstacle(this.synchronizer);
        this.display = new Display(this.synchronizer);
        this.control = new Control(this.synchronizer);
        this.food = new Food(this.synchronizer);
        this.gameEngine = new GameEngine(this.snake, this.display, this.control, this.food, 5, this.synchronizer);
    }

    //METHODS

    /**
     * Method createNewGame
     * Create a new world in which places
     * the Obstacles, the Snake and the Food each time in the game area
     *
     * @param lvl is the level chosen for the game
     */
    public void createNewGame(int lvl) 
    {
        this.obstacle = new Obstacle(lvl, this.synchronizer);    //creates new objects to be placed in the area
        this.gameEngine.startYourEngines();                      //starts a new timer when the new game is created


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

