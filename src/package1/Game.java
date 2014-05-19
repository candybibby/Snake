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




    public Game()
    {


    }

    /**
     * Method runNewGame
     * only called from within this class
     *
     * @param lvl
     */
    public void runNewGame(int lvl)
    {
        Synchronizer.resetGame();                             //starts a fresh game
        world = new World(lvl);                 // make an instance of world and pass it the synchronizer
        world.createNewGame();                             //calls the createNewGame method and sets level param
    }


	public World getWorld() {
		return this.world;
	}


}
