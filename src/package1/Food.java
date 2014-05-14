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
    private Synchronizer synchronizer;

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
     * Empty constructor
     * Place the food in the middle of the area by default
     */
    public Food() {
        this.synchronizer = new Synchronizer();
        //this.abscissa = (int) (Math.random() * synchronizer.getGameAreaWidth());
        //this.ordinate = (int) (Math.random() * synchronizer.getGameAreaHeight());
        this.foodElement = FOOD_ELEMENT_DEFAULT;
    }


    /**
     * Constructor
     * Place the food in the middle of the area by default
     */
    public Food(Synchronizer synch) {
        this.synchronizer = synch;
        //this.abscissa = (int) (Math.random() * synchronizer.getGameAreaWidth());
        //this.ordinate = (int) (Math.random() * synchronizer.getGameAreaHeight());
        this.foodElement = FOOD_ELEMENT_DEFAULT;
    }

    /**
     * Constructor with 2 param
     * Initialize the place with the food given
     *
     * @param abs , abscissa given
     * @param ord , ordinate given
     */
    public Food(int abs, int ord) {
        this.synchronizer = new Synchronizer();
        if (abs < 0)
            abs = 0;
        this.abscissa = abs;
        if (ord < 0)
            ord = 0;
        this.ordinate = ord;
        this.foodElement = FOOD_ELEMENT_DEFAULT;
    }


    /**
     * Constructor with 1 param
     * Initialize the food element with the one given
     *
     * @param elem , food given
     */
    public Food(char elem) {
        this.synchronizer = new Synchronizer();
        this.abscissa = (int) Math.random() * synchronizer.getGameAreaWidth();
        this.ordinate = (int) Math.random() * synchronizer.getGameAreaHeight();
        if ((elem == '\0') || (elem == ' '))
            elem = FOOD_ELEMENT_DEFAULT;
        this.foodElement = elem;
    }


    /**
     * Constructor with 3 param
     * Initialize the food on a given place
     * And the food element with the one given
     *
     * @param abs
     * @param ord
     * @param elem
     */
    public Food(int abs, int ord, char elem) {
        this.synchronizer = new Synchronizer();
        if (abs < 0)
            abs = (int) Math.random() * synchronizer.getGameAreaWidth();
        this.abscissa = abs;
        if (ord < 0)
            ord = (int) Math.random() * synchronizer.getGameAreaHeight();
        this.ordinate = ord;
        if ((elem == '\0') || (elem == ' '))
            elem = FOOD_ELEMENT_DEFAULT;
        this.foodElement = elem;
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
        char[][] tempWorld = synchronizer.getGameWorld();

        while (!synchronizer.isFoodPresent()) {
            if (this.synchronizer.isEmpty(this.abscissa, this.ordinate)) {                        // if the square is empty
                tempWorld[this.abscissa][this.ordinate] = this.foodElement;
                synchronizer.setGameWorld(tempWorld);        // we can place the food in this square
                // the food is placed
                synchronizer.setFoodPresent(true);
            } else {                                                                                // else
                int x = (int) (Math.random() * synchronizer.getGameAreaWidth());                            // we have to set the coordinates
                int y = (int) (Math.random() * synchronizer.getGameAreaHeight());                        // of the food
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
     * @return
     */
    public char getFoodElement() {
        return this.foodElement;
    }

    public Synchronizer getSynchronizer() {
        return this.synchronizer;
    }


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