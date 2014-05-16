/**
 * Created by UK
 * Class Snake
 * In charge of snake itself, it's movement and growth
 */

import java.util.ArrayList;

public class Snake{ 
	
	private ArrayList<Character> snake; //List of the char modelling pieces of a snake, linked with related coordinates.
	private ArrayList<Integer> abscissa;//List of abscissas (x) position of each piece.
	private ArrayList<Integer> ordinate;//List of ordinate (y) position of each piece.

	private final char head = Symbol.HEAD;
	private final char body = Symbol.BODY;
	private Synchronizer sync;

	/** CONSTRUCTOR
	 *  It creates an instance of Snake, with an head and 2 pieces for the body.
	 *  It will be placed in the middle of the World, right-oriented.
	 *  @param sync
	 */

	public Snake(Synchronizer sync){
		this.sync = sync;
		snake = new ArrayList<Character>();
		abscissa = new ArrayList<Integer>();
		ordinate = new ArrayList<Integer>();
		
		snake.add(head);
		snake.add(body);
		snake.add(body);
		
		int midX = (int)(this.sync.getGameAreaWidth())/2;
		int midY = (int)(this.sync.getGameAreaHeight())/2;
		abscissa.add(midX);
		ordinate.add(midY);
		abscissa.add(midX-1);
		ordinate.add(midY);
		abscissa.add(midX-1-1);
		ordinate.add(midY);
		updateMatrix();
		}

	/** Method updateMatrix
	 *  that reads the lists describing the snake and writes in the matrix, placing all the pieces in the right position.
	 */
	private void updateMatrix() {
		for (int i = 0; i < snake.size(); i++)
			sync.getGameWorld()[abscissa.get(i)][ordinate.get(i)] = snake.get(i);
	}

	/** Method updateMatrix,
	 *  as over there, clear the tail in the matrix. When the tail moves, it clear traces.
	 *  @param removeX
	 *  @param removeY 
	 */

	private void updateMatrix(int removeX, int removeY) {
		sync.getGameWorld()[removeX][removeY] = Symbol.EMPTY;
		updateMatrix();
	}

	/** Method move()
	 *  gets from Synchronizer the direction and move the snake.
	 *  if Snake eat some food, it grows,
	 *  if Snake crashes into itself or into walls, it dies. Poor snake!
	 */

	public int move () {
		Command d = Synchronizer.getLastButtonPressed()[0];
		if (!(d instanceof Direction))
			return -1;

		int nextX = abscissa.get(0);
		int nextY = ordinate.get(0);

		switch (d.getValue()){
            case Direction.UP: 	nextY--;
                  					break;

            case Direction.DOWN: nextY++;
		                       	   break;

            case Direction.LEFT: nextX--;
                          		   break;

            case Direction.RIGHT: nextX++; 
                            		 break;
                }
		
	/** Snake moves towards an empty cell.
	 *  Simply translate Snake, adding a 
	 *  if Snake eat some food, it grows,
	 *  if Snake crashes into itself or into walls, it dies. Poor snake!
	 */
	
		if(sync.isEmpty(nextX,nextY)){ 
			ordinate.add(0,nextY);
			abscissa.add(0,nextX);
			int lastX = abscissa.get(abscissa.size()-1);
			int lastY = ordinate.get(ordinate.size()-1);
			abscissa.remove(abscissa.size()-1);
			ordinate.remove(ordinate.size()-1);
			updateMatrix(lastX, lastY);
			}
		else if(sync.isFood(nextX,nextY)){
			ordinate.add(0,nextY);
			abscissa.add(0,nextX);
			snake.add(1, Symbol.BODY);
			updateMatrix();
            sync.setFoodPresent(false);
            sync.increaseScore(1);
			}
		else{
            ordinate.add(0,nextY);
            abscissa.add(0,nextX);
            int lastX = abscissa.get(abscissa.size()-1);
            int lastY = ordinate.get(ordinate.size()-1);
            abscissa.remove(abscissa.size()-1);
            ordinate.remove(ordinate.size()-1);
            updateMatrix(lastX, lastY);
			sync.theGameIsOver();
			}
		return 0;		
	}
}
