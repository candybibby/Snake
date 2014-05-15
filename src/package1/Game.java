package package1;

/**
 * Created by Tariq on 18.4.2014.
 * Class Game
 */
public class Game 
{ 
    /**
     * Instance variables
     */
     
    //world  
    public World world;
    //synchronizer
    public Synchronizer synchronizer;



    public Game(Synchronizer synchronizer) 
    {
        this.synchronizer = synchronizer;

    }

    /**
     * Method runNewGame
     * only called from within this class
     *
     * @param lvl
     */
    public void runNewGame(int lvl)
    {
        synchronizer.resetGame();                             //starts a fresh game
        world = new World(this.synchronizer);                 // make an instance of world and pass it the synchronizer
        world.createNewGame(lvl);                             //calls the createNewGame method and sets level param
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
