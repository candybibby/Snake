package package1;
import java.io.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Character> snake; //List of the char modelling pieces of a snake, linked with related coordinates.
    private ArrayList<Integer> abscissa;//List of abscissas (x) position of each piece.
    private ArrayList<Integer> ordinate;//List of ordinate (y) position of each piece.

    private final char head = Symbol.HEAD; //represents the head of the snake
    private final char body = Symbol.BODY; //represents the body of the snake


    /**
     * CONSTRUCTOR
     * It creates an instance of Snake, with an head and 2 pieces for the body.
     * It will be placed in the middle of the World, right-oriented.
     *
     * @param 
     */

    public Snake() {
        
        snake = new ArrayList<Character>(); //creates an arrayList of character entry 
        abscissa = new ArrayList<Integer>(); //the coordinates for the placing of the arrayList 
        ordinate = new ArrayList<Integer>();

        snake.add(head); //adds the snake head
        snake.add(body); //adds the snake body
        snake.add(body); //adds the snake body

        int midX = (int) (Synchronizer.getGameAreaWidth()) / 2; //places the snake in the centre of the game
        int midY = (int) (Synchronizer.getGameAreaHeight()) / 2; //divided by 2 because height divided by 2 = centre
        abscissa.add(midX);
        ordinate.add(midY);
        abscissa.add(midX - 1);
        ordinate.add(midY);
        abscissa.add(midX - 1 - 1);
        ordinate.add(midY);
        updateMatrix();
    }

    /**
     * Method updateMatrix
     * that reads the lists describing the snake and writes in the matrix, placing all the pieces in the right position.
     */
    private void updateMatrix() {
        for (int i = 0; i < snake.size(); i++)
            Synchronizer.getGameWorld()[abscissa.get(i)][ordinate.get(i)] = snake.get(i);
    }

    /**
     * Method updateMatrix,
     * as over there, clear the tail in the matrix. When the tail moves, it clear traces.
     *
     * @param removeX
     * @param removeY
     */

    private void updateMatrix(int removeX, int removeY) {
        Synchronizer.getGameWorld()[removeX][removeY] = Symbol.EMPTY;
        updateMatrix();
    }

    /**
     * Method move()
     * gets from Synchronizer the direction and move the snake.
     * if Snake eat some food, it grows,
     * if Snake crashes into itself or into walls, it dies. Poor snake!
     */

    public int move() {
        Command d = Synchronizer.getLastButtonPressed()[0];
        if (!(d instanceof Direction))
            return -1;

        int nextX = abscissa.get(0);
        int nextY = ordinate.get(0);

        switch (d.getValue()) {
            case Direction.UP:
                nextY--;
                break;

            case Direction.DOWN:
                nextY++;
                break;

            case Direction.LEFT:
                nextX--;
                break;

            case Direction.RIGHT:
                nextX++;
                break;
        }

        /** Snake moves towards an empty cell.
         *  Simply translate Snake, adding a
         *  if Snake eat some food, it grows,
         *  if Snake crashes into itself or into walls, it dies. Poor snake!
         */

        if (Synchronizer.isEmpty(nextX, nextY)) {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            int lastX = abscissa.get(abscissa.size() - 1);
            int lastY = ordinate.get(ordinate.size() - 1);
            abscissa.remove(abscissa.size() - 1);
            ordinate.remove(ordinate.size() - 1);
            if (!Synchronizer.TRON)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
        } else if (Synchronizer.isFood(nextX, nextY)) {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            snake.add(1, Symbol.BODY);
            updateMatrix();
            Food [] food = Synchronizer.getFood();
            for (int i=0; i<food.length; i++)
                if ((food[i].getX() == nextX) && (food[i].getY() == nextY)) 
                    food[i].setFoodPresent(false);
            Synchronizer.increaseScore(1);
        } else {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            int lastX = abscissa.get(abscissa.size() - 1);
            int lastY = ordinate.get(ordinate.size() - 1);
            abscissa.remove(abscissa.size() - 1);
            ordinate.remove(ordinate.size() - 1);
            if (!Synchronizer.TRON)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
            Synchronizer.theGameIsOver();
            try {
				Synchronizer.saveHighScore("highScore.txt");
			}
			catch(IOException e){}
        }
        return 0;
    }

    public Snake reset() {
        for (int i = 0; i < abscissa.size() - 1; i++)
            Synchronizer.getGameWorld()[abscissa.get(i)][ordinate.get(i)] = Symbol.EMPTY;
        return new Snake();
    }
    
    /**
	 * @return the snake
	 */
	public ArrayList<Character> getSnake() {
		return this.snake;
	}

	/**
	 * @return the abscissa
	 */
	public ArrayList<Integer> getAbscissa() {
		return this.abscissa;
	}

	/**
	 * @return the ordinate
	 */
	public ArrayList<Integer> getOrdinate() {
		return this.ordinate;
	}

	/**
	 * @return the sync
	 */
	/*public Synchronizer getSynchronizer() {
		return Synchronizer;
	}*/

}

