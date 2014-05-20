package package1;
import java.io.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Character> snake; //List of the char modelling pieces of a snake, linked with related coordinates.
    private ArrayList<Integer> abscissa;//List of abscissas (x) position of each piece.
    private ArrayList<Integer> ordinate;//List of ordinate (y) position of each piece.

    private int id;


    /**
     * CONSTRUCTOR
     * It creates an instance of Snake, with an head and 2 pieces for the body.
     * It will be placed in the middle of the World, right-oriented.
     *
     * @param bornX X position of born
     * @param bornY Y position of born
     * @param id snake's id
     */

    public Snake(int bornX, int bornY, int id) {
        this.id = id;
        snake = new ArrayList<Character>(); //creates an arrayList of character entry 
        abscissa = new ArrayList<Integer>(); //the coordinates for the placing of the arrayList 
        ordinate = new ArrayList<Integer>();

        snake.add(Symbol.HEAD[id]); //adds the snake head
        snake.add(Symbol.BODY[id]); //adds the snake body
        snake.add(Symbol.BODY[id]); //adds the snake body
        ordinate.add(bornY);
        ordinate.add(bornY);
        ordinate.add(bornY);
        abscissa.add(bornX);
        if (id==0) {
            abscissa.add(bornX - 1);
            abscissa.add(bornX - 1 - 1);
        }
        else {
            abscissa.add(bornX + 1);
            abscissa.add(bornX + 1 + 1);
        }
        updateMatrix();
    }

    public Snake() {
        this((int) (Synchronizer.getGameAreaWidth())/2, (int) (Synchronizer.getGameAreaHeight()) / 2,0);
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
        Command d = Synchronizer.getLastButtonPressed(id);
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
            if (!Synchronizer.tron)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
        } else if (Synchronizer.isFood(nextX, nextY)) {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            snake.add(1, Symbol.BODY[id]);
            updateMatrix();
            Food [] food = Synchronizer.getFood();
            for (int i=0; i<food.length; i++)
                if ((food[i].getX() == nextX) && (food[i].getY() == nextY)) 
                    food[i].setFoodPresent(false);
            Synchronizer.increaseScore(id);
        } else {
            ordinate.add(0, nextY);
            abscissa.add(0, nextX);
            int lastX = abscissa.get(abscissa.size() - 1);
            int lastY = ordinate.get(ordinate.size() - 1);
            abscissa.remove(abscissa.size() - 1);
            ordinate.remove(ordinate.size() - 1);
            if (!Synchronizer.tron)
            	updateMatrix(lastX, lastY);
            else
            	updateMatrix();
            Synchronizer.setSnakeStillAlive(false, id);
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


}

