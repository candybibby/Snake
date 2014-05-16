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
 * 5. Synchronizer
 */
public class GameEngine {


    // variables from the outside

    private Snake snake;
    private int frameRate;

    private Food [] food;

    // in-class variables
    public Timer engineTimer;

    /**
     * Constructor with 5 parameters
     * Initialize the variables of the class
     *
     * @param snake


     * @param frameRate
         */
    public GameEngine(Snake snake, int frameRate, Food [] food) {
        this.snake = snake;
        this.frameRate = frameRate;
        this.food = food;
    }

    /**
     * Method startYourEngines
     */
    public void startYourEngines() {                                    // start the timer to run the game
        if (!Synchronizer.isGameLoopRunning()) {
            Synchronizer.setGameLoopRunning(true);

            engineTimer = new Timer();
            engineTimer.schedule(new TimerTask() {                          // a timer to run our game loop
                @Override
                public void run() {
                    if (!Synchronizer.isStopGameLoop()) {

                        update();                                               // run these two

                    } else {
                        Synchronizer.setGameLoopRunning(false);
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
        if (Synchronizer.isGameOver()){
            Synchronizer.setStopGameLoop(true);
        }

		/*for (Snake snake : snakes) {       // this loop goes over every snake in the snakes array

			// put code for updating and movin snake here
			// which could look like this
			// snake.move()


		}*/


    }





}
