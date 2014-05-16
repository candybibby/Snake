/* I made this example class only to explain you how to use more
   functionally our classes, and to facilitate your work
   to understand in which situation you should to use them.
 */



public class TestControlDirection{
	public static void main(String []args){
		Synchronizer sync = new Synchronizer(80);   //Only to debug this class and methods
		
		System.out.println("\n\n...Push w/a/s/d to set a direction and then push ENTER to confirm your choice...\n");
		
		while(true){
			/* This part will be placed in GameEngine to update 
			   lastButtonPressed parameter (in Synchronizer) every time a player
			   press a key. 
			   This method parses a key, creates a direction associated to input
			   and updates Synchronizer's lastButtonPressed parameter. 
			   The other classes(Snake) should to ask it to take the direction (Synchronizer.getLastButtonPressed()). 
			   This is already ready to a next improving, infact 
			   it supports multiple instances of direction with an array of it,
			   but now we should use only the first position of it for single player mode.
			 */
			Control.updateDirection();
			
			
			/*  ##More functional implementation##
				While this part will be put in Snake's move() method
				to fetch a new Direction (from Synchronizer) and decide the action to do.
			 */
			Command d = Synchronizer.getLastButtonPressed()[0];
			switch (d.getValue()){
            case Direction.UP: //moves snake up
											System.out.println("UP");        //simple debug 
                  					break;
            case Direction.DOWN: //moves snake down
											System.out.println("DOWN");        //simple debug 
		                       	   break;
            case Direction.LEFT: //moves snake left
										 	System.out.println("LEFT");        //simple debug 
                          		   break;
            case Direction.RIGHT: //moves snake right
											System.out.println("RIGHT");        //simple debug 
                            		break;
                }
			}
		}
	}
