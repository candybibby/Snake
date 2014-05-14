package package1;

/**
 * World class
 * <p/>
 * UK People
 */
public class World {

    //ATTRIBUTES


    /**
     * Synchronizer
     */
    private Synchronizer synchronizer;

    /**
     * Snake
     */
    private Snake snake;

    /**
     * Food
     */
    private Food food;

    /**
     * Obstacle
     */
    private Obstacle obstacle;

    public GameEngine gameEngine;

    private Display display;

    private Control control;


    //CONSTRUCTORS

    /**
     * Empty constructor
     * Initialize all the attributes by default
     * and create a new World
     */


    /**
     * Constructor
     * Initialize all the attributes by default
     * and create a new World
     */
    public World(Synchronizer synch) {
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
     * Create a new world in which we places
     * the obstacles, the Snake and the food each time in the area
     *
     * @param lvl the level chosen for the game
     */
    public void createNewGame(int lvl) {
        this.obstacle = new Obstacle(lvl, this.synchronizer);    //create new obstacle placed in the area
        this.gameEngine.startYourEngines();


        //while the snake is alive the game continues
        //and places the food randomly
        //and the snake can move
       /*while(snakeAlive){
		   this.snake.Movet();
		   this.food.placeFood();
		   snakeAlive = this.synchronizer.getSnakeStillAlive();		//check each time is the snake is alive
	   }*/
    }

    //world.setNewSnake(world.getSnake().reset())
}