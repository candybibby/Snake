package package1;
import java.io.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Character> snake; //List of the char modelling pieces of a snake, linked with related coordinates.
    private ArrayList<Integer> abscissa;//List of abscissas (x) position of each piece.
    private ArrayList<Integer> ordinate;//List of ordinate (y) position of each piece.

    private final char head = Symbol.HEAD;
    private final char body = Symbol.BODY;
    private Synchronizer synchronizer;

    /**
     * CONSTRUCTOR
     * It creates an instance of Snake, with an head and 2 pieces for the body.
     * It will be placed in the middle of the World, right-oriented.
     *
     * @param sync
     */

    public Snake(Synchronizer sync) {
        this.synchronizer = sync;
        snake = new ArrayList<Character>();
        abscissa = new ArrayList<Integer>();
        ordinate = new ArrayList<Integer>();

        snake.add(head);
        snake.add(body);
        snake.add(body);

        int midX = (int) (this.synchronizer.getGameAreaWidth()) / 2;
        int midY = (int) (this.synchronizer.getGameAreaHeight()) / 2;
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
            synchronizer.getGameWorld()[abscissa.get(i)][ordinate.get(i)] = snake.get(i);
    }

    /**
     * Method updateMatrix,
     * as over there, clear the tail in the matrix. When the tail moves, it clear traces.
     *
     * @param removeX
     * @param removeY
     */

    private void updateMatrix(int removeX, int removeY) {
        synchronizer.getGameWorld()[removeX][removeY] = Symbol.EMPTY;
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

        if (synchronizer.isEmpty(nextX, nextY)) {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            int lastX = abscissa.get(abscissa.size() - 1);
            int lastY = ordinate.get(ordinate.size() - 1);
            abscissa.remove(abscissa.size() - 1);
            ordinate.remove(ordinate.size() - 1);
            if (!synchronizer.TRON)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
        } else if (synchronizer.isFood(nextX, nextY)) {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            snake.add(1, Symbol.BODY);
            updateMatrix();
            Food [] food = synchronizer.getFood();
            for (int i=0; i<food.length; i++)
                if ((food[i].getX() == nextX) && (food[i].getY() == nextY)) 
                    food[i].setFoodPresent(false);
            synchronizer.increaseScore(1);
        } else {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            int lastX = abscissa.get(abscissa.size() - 1);
            int lastY = ordinate.get(ordinate.size() - 1);
            abscissa.remove(abscissa.size() - 1);
            ordinate.remove(ordinate.size() - 1);
            if (!synchronizer.TRON)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
            synchronizer.theGameIsOver();
            try {
				this.synchronizer.saveHighScore("highScore.txt");
			}
			catch(IOException e){}
        }
        return 0;
    }

    public Snake reset() {
        for (int i = 0; i < abscissa.size() - 1; i++)
            synchronizer.getGameWorld()[abscissa.get(i)][ordinate.get(i)] = Symbol.EMPTY;
        return new Snake(synchronizer);
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
	public Synchronizer getSynchronizer() {
		return this.synchronizer;
	}

}

