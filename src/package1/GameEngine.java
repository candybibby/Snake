package package1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tariq on 18.4.2014.
 * Class GameEngine
 * this needs the following instances to run an iteration of the game
 * 1.(an array with) all the Snakes
 * 2. display
 * 3. world
 * 4. control
 * 5. synchronizer
 */
public class GameEngine {


    // variables from the outside

    private Snake snake;
    private Display display;
    private Control control;
    private int frameRate;
    private Synchronizer synchronizer;
    private Food [] food;

    // in-class variables
    public Timer engineTimer;

    /**
     * Constructor with 5 parameters
     * Initialize the variables of the class
     *
     * @param snake
     * @param display
     * @param control
     * @param frameRate
     * @param synchronizer
     */
    public GameEngine(Snake snake, Display display, Control control, int frameRate, Synchronizer synchronizer, Food [] food) {
        this.snake = snake;
        this.control = control;   //this class use static method so an instance of it is unuseful
        this.display = display;
        this.frameRate = frameRate;
        this.synchronizer = synchronizer;
        this.food = food;
    }

    /**
     * Method startYourEngines
     */
    public void startYourEngines() {                                    // start the timer to run the game
        if (!synchronizer.isGameLoopRunning()) {
            synchronizer.setGameLoopRunning(true);

            engineTimer = new Timer();
            engineTimer.schedule(new TimerTask() {                          // a timer to run our game loop
                @Override
                public void run() {
                    if (!synchronizer.isStopGameLoop()) {

                        update();                                               // run these two
                        render();                                               // for ever and ever
                    } else {
                        synchronizer.setGameLoopRunning(false);
                        System.out.println("Game Over!");
                        engineTimer.cancel();
                        engineTimer.purge();

                    }

                }
            }, 0, 1000 / frameRate);    // arguments are delay and interval, so for example 1000 ms / framerate of 5
            // makes this loop run every 200 ms
        }
    }

    /**
     * Method update
     */
    public void update() {

        for (int i = 0; i< Synchronizer.FOOD_NUMBER; i++)
            food[i].placeFood();
        //Control.updateDirection();
        snake.move();
        if (synchronizer.isGameOver()){
            synchronizer.setStopGameLoop(true);
        }

		/*for (Snake snake : snakes) {       // this loop goes over every snake in the snakes array

			// put code for updating and movin snake here
			// which could look like this
			// snake.move()


		}*/


    }

    /**
     * Method render
     * should draw a new game screen based on the input
     * and calculations done in the update function
     */
    public void render() {

        //display.printGame();


    }


}
