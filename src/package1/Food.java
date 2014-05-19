package package1;

/**
 * @author French People
 *         Class food
 *         models food, creates food elements and drops them in a valid space.
 */

public class Food {

    // ATTRIBUTES

    /**
     * Synchronizer
     */


    /**
     * x of the point where will be placed the food
     */
    private int abscissa;

    /**
     * y of the point where will be placed the food
     */
    private int ordinate;

    /**
     * Food element generated, different type possible
     */
    private char foodElement;

    /**
     * The value by default of FoodElement generated, 'm' for "mouse"
     */
    public final static char FOOD_ELEMENT_DEFAULT = 'm';

    /**
     * True only if there is a food element in the area
     */
    public boolean foodPresent;


    // CONSTRUCTORS

    /**
     * Constructor
     * Place the default food in a random empty cell
     */
    public Food() {
        this(Symbol.MOUSE);
    }

    /**
     * Constructor with 2 param
     * Initialize the place with the food given
     *
     * @param
     * @param abs , abscissa given
     * @param ord , ordinate given
     */
    public Food(int abs, int ord) {
        this( abs, ord, Symbol.MOUSE);
    }


    /**
     * Constructor with 1 param
     * Initialize the food element with the one given
     *
     * @param elem , food given
     */
    public Food(char elem) {
        this( 0, 0, elem);
        synchronizer.writeThisCell(abscissa, ordinate, 'X');
        int x,y;
        do {
            x = (int) (Math.random() * Synchronizer.getGameAreaWidth());
            y = (int) (Math.random() * Synchronizer.getGameAreaHeight());
        } while (!Synchronizer.isEmpty(x, y));
        this.setX(x);
        this.setY(y);
        Synchronizer.writeThisCell(abscissa, ordinate, foodElement);
    }


    /**
     * Constructor with 4 param
     * Initialize the food on a given place
     * And the food element with the one given
     *
     * @param
     * @param abs
     * @param ord
     * @param elem
     */
    public Food(int abs, int ord, char elem) {
        
        this.abscissa = abs;
        this.ordinate = ord;
        this.foodElement = elem;
        this.foodPresent = true;
        Synchronizer.writeThisCell(abscissa, ordinate, foodElement);

    }


    //METHODS


    /**
     * Method placeFood
     * which place randomly the food element (char)
     * in the game area
     * if the place is free -> add it
     * else -> check for another
     */

    public void placeFood() {

        char[][] tempWorld = Synchronizer.getGameWorld();

        while (!this.isFoodPresent()) {
            if (Synchronizer.isEmpty(this.abscissa, this.ordinate)) {                        // if the square is empty
                tempWorld[this.abscissa][this.ordinate] = this.foodElement;
                Synchronizer.setGameWorld(tempWorld);        // we can place the food in this square
                // the food is placed
                this.setFoodPresent(true);
            } else {                                                                                // else
                int x = (int) (Math.random() * Synchronizer.getGameAreaWidth());                            // we have to set the coordinates
                int y = (int) (Math.random() * Synchronizer.getGameAreaHeight());                        // of the food
                this.setX(x);                                                                        // to place it in an empty square
                this.setY(y);
            }
        }
    }


    /**
     * Getter of the abscissa
     *
     * @return X
     */
    public int getX() {
        return this.abscissa;
    }

    /**
     * Getter of the ordinate
     *
     * @return Y
     */
    public int getY() {
        return this.ordinate;
    }

    /**
     * Getter of the foodElement
     *
     * @return foodElement
     */
    public char getFoodElement() {
        return this.foodElement;
    }

    /*public Synchronizer getSynchronizer() {
        return this.Synchronizer;
    }*/


    /**
     * Setter of the abscissa
     *
     * @param abs
     */
    public void setX(int abs) {
        if (abs >= 0)
            this.abscissa = abs;
    }


    /**
     * Setter of the ordinate
     *
     * @param ord
     */
    public void setY(int ord) {
        if (ord >= 0)
            this.ordinate = ord;
    }


    /**
     * Setter of the foodElement
     * Change the food element by the one given
     *
     * @param elem
     */
    public void setFoodElement(char elem) {
        if (!(elem == '\0' || elem == ' '))
            this.foodElement = elem;
    }

    /**
     * Getter of foodPresent
     *
     * @return boolean
     */
    public boolean isFoodPresent() {
        return foodPresent;
    }

    /**
     * Setter of foodPresent
     * Set the value of foodPresent
     *
     * @param fP
     */
    public void setFoodPresent(boolean fP) {
        this.foodPresent = fP;
    }


}
