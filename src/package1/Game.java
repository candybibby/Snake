package package1;

/**
 * Created by Tariq on 18.4.2014.
 * Class Game
 */
public class Game {

    private World world;
    public Synchronizer synchronizer;


    /**
     * Method runNewGame
     * only called from within this class
     *
     * @param lvl
     */
    public void runNewGame(int lvl) {
        synchronizer = new Synchronizer();               // make an instance of synchronizer
        world = new World(synchronizer);                 // make an instance of world and pass it the synchronizer
        world.createNewGame(lvl);
    }


    /**
     * Method runUntilOver
     * runs until game is over
     *
     * @param lvl
     */
    /*public void runUntilOver(int lvl) {
        boolean stopGameNow = false;


        // so every time the user resets the game, it is restarted
        while (!stopGameNow) {              // but when the user dies, then that's it
            runNewGame(lvl);
            System.out.println("afsdfasdf");
        }
        stopGameNow = synchronizer.isGameOver();
        System.out.println("Game over!");
    }*/


}
