package package1; /**
 * Created by UK
 * Class Snake
 * In charge of snake itself, it's movement and growth
 */

/**Allows complier to know about library class*/

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class OldSnake
{



				// ATTRIBUTES 


	/** Synchronizer*/
	private Synchronizer synchronizer;
	
	/** Food*/
	
	private Food food;
	

						//ARRAYLIST - CREATING THE SNAKE

	/**Delcaring instant variables arrayList and ArrayList<Character>*/
	private Character character; 
	private ArrayList<Character> arrayList;


						//PLACING THE SNAKE

	/** x of the point where will be placed the snake */
	private int abscissa ;

	/** y of the point where will be placed the snake*/
	private int ordinate;

	/** The default value for placing the Arraylist - snake into the game. 'S' and 'O' */
	public final static char ARRAY_LIST_DEFAULT = 's';




				// CONSTRUCTORS


	/**The contructor for the snake class, creates a new ArrayList*/
	public OldSnake(Character c) {
		this.character = c;
		this.arrayList = new ArrayList<Character>();
	}

	/**Place the Snake in the middle of the area by default*/
	public OldSnake(){
		this.synchronizer = new Synchronizer();
		this.abscissa = (int)(this.synchronizer.getGameAreaWidth())/2;
		this.ordinate = (int)(this.synchronizer.getGameAreaHeight())/2;
		this.character = ARRAY_LIST_DEFAULT;
	}
     

     
     			// METHODS 

     
     /**the getS method, returns the value for s that will be used in the add method*/
     public ArrayList<Character> getS()  { 
         return this.arrayList; 
     } 
     
     /** getArrayList method, returns the position of the value in the list*/
     public Character getArrayList() {
        return character;
     }
     
     /**adds the value of the snake, for example S or O*/
      public void addChar(Character newChar) 
     { 
         this.arrayList.add('S'); 
         this.arrayList.add('O');
     } 
     
           
    /**sets the values for x and y to be in the centre of the game world*/
    public void placeSnake(){
        int x = (int)(this.synchronizer.getGameAreaWidth())/2;			//    " /2 " to place the snake in the center 				
    	int y = (int)(this.synchronizer.getGameAreaHeight())/2;						
    	this.setX(x); 																		
    	this.setY(y);
    }
    	
    /**Gets the x value, abscissa*/
    public int getX(){
    	return this.abscissa;
    }
    
    /**Gets the y value, ordinate*/
    public int getY(){
    	return this.ordinate;
    }
    
    /**Set the x value, abscissa*/
      public void setX(int abs){
    	if (abs >= 0)
    		this.abscissa = abs;
    }

    /**Set the y value, ordinate*/
    public void setY(int ord){
    	if (ord >= 0)
    		this.ordinate = ord;
    }
    
    /**Method that references the placeFood method from the Food Class*/
    
    public void foodLink(){
        Food F = new Food();
        F.placeFood();
    }
    
    
            /** MOVEMENT OF SNAKE*/
    
    /** Declaring coordinates for movement of the snake*/
    
    private LinkedList<Point> snake;
    
    /** Declaring direction, which decides the direction the snake moves in*/
    
    private int direction;
    
    /** The Move method, which allows the snake to travel in four directions "up", "down", "left" and "right"*/
    
    public void Move ()
        {
                Point c = snake.peekFirst();
                Point newPoint = c;
               
                switch (direction){
                    case Direction.UP: //moves snake up
                            newPoint = new Point(c.x, c.y - 1);
                            break;
                    case Direction.DOWN: //moves snake down
                            newPoint = new Point(c.x, c.y + 1);
                            break;
                    case Direction.LEFT: //moves snake left
                            newPoint = new Point(c.x - 1, c.y);
                            break;
                    case Direction.RIGHT: //moves snake right
                            newPoint = new Point(c.x + 1, c.y);
                            break;
                }
               
                /** Adds to the front of the snake depending on direction depends on were it will appear and back area will be deleted, creates an illusion of movement*/
                
                snake.remove(snake.peekLast()); 
               
                /**GROWTH OF SNAKE*/
                
                /** If statement that causes the Snake to grow bigger then it collides with a food object*/
                if (newPoint.equals(food))
		{
			/** The Snake has hit the food*/
			
			Point addPoint = (Point) newPoint.clone();
			
			switch (direction) {
			case Direction.UP:
				newPoint = new Point(c.x, c.y - 1);
				break;
			case Direction.DOWN:
				newPoint = new Point(c.x, c.y + 1);
				break;
			case Direction.LEFT:
				newPoint = new Point(c.x - 1, c.y);
				break;
			case Direction.RIGHT:
				newPoint = new Point(c.x + 1, c.y);
				break;
			}
			
			/**Snake grows bigger by one piece*/
			
			snake.push(addPoint);
			foodLink();                    /**foodLink references the placeFood method*/
			
		}
		else if (newPoint.x < 0 || newPoint.x > (synchronizer.gameAreaWidth- 1))
		{
			/** Snake died, reset game*/
			
			placeSnake();
			return;
		}
		else if (newPoint.y < 0 || newPoint.y > (synchronizer.gameAreaHeight - 1))
		{
			/** Snake died, reset game*/
			
			placeSnake();
			return;
		}
		else if (snake.contains(newPoint))
		{
			/** Snake ran into itself, reset game*/
			
			placeSnake();
			return;
		}

		
		snake.push(newPoint);
	}
	
	/**DEATH OF SNAKE - Ryans stuff but may not be needed due to code above.Also need to fix errors if being used instead. Find out on Tuesday 
    public void collision(){
          for(int i = 0; i < this.arrayList.size(); i++){  
                    for(int j = 0; j < Wall.size(); j++){  
                              if(this.arrayList.get(i).bounds().intersects(Wall.get(j).bounds())){  
                                        System.out.println("Game Over!");  
                              }  
                    }  
         } 

        }
        */
	
           
    }
