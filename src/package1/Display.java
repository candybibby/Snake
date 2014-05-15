package package1;

/**
 * Display class.
 * For score printing, we are waiting Synchronizer Class to implement getScore() method.
 *
 * @author Italian People
 */
public class Display 
   {
                               //ATTRIBUTES
    /**
     * Instance Variables
     */
     
    //Synchronizer             
    private Synchronizer sync;
    /**
     * Local variables for the game area
     */
    private int height, width;

    /**
     * Constructor with 1 parameter.
     * Inizializes parameter and sets the screen size.
     *
     * @param sync
     */
    public Display(Synchronizer sync) 
    {
        this.sync = sync;                                   //creates sync object instance
        height = sync.getGameAreaHeight();                  //gets game area height
        width = sync.getGameAreaWidth();                    //gets game width
    }

    /**
     * Refresh matrix and print game's screen.
     */
    public void printGame()
    {
        char[][] gameWorld = sync.getGameWorld();                            
        System.out.println("\nScore: " + sync.getScores()[0] + "\n");     //prints the players score

        for (int i = 0; i < height; i++)                                  //refreshes matrix
        {
            for (int j = 0; j < width; j++) 
            {


                if (gameWorld[j][i] == '\0')                              //prints game
                {
                    System.out.print("  ");
                } 
                else                                                       
                {
                    System.out.print(gameWorld[j][i] + " ");
                }
            }
            System.out.println();
        }
    }
    /**
     * prints characters 
     */
    private static void printLineOf(char c, int len)                     
    {
        for (int i = 0; i < len; i++)
            System.out.print(c);
        System.out.println();
    }


}
