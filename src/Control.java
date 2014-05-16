/**
 * Control Class.
 * This class is useful to manage Input user interaction.
 * @author Italian People
 */


import java.util.Scanner;

public class Control{
	
  	private Synchronizer sync;
//Default direction. When the game starts, Snake goes right (like Nokia 3310 :))
	static Direction[] arrow = { new Direction(Direction.RIGHT) };
	
	
	/** 
	 * Constructor with 1 parameter
	 * Create an instance of Control class with the specified Synchronizer and
	 * set a default Direction to each player.
	 * @param sync
	 */
		
	public Control(Synchronizer sync){
		this.sync = sync;
		//arrow = new Direction [sync.getPlayerNumber()]();
		for (int i = 0; i < arrow.length; i++)
			arrow[i] = new Direction(Direction.RIGHT);
		}

	/** 
	 * This method use a Scanner and asks the player its input, but he must push enter to confirm 
	 * his comand and for this reason this version isn't dynamic.
	 * This also update the lastButtonPressed parameter of Synchronizer!
	 * @return an array of Command
         */ 
	public static Command[] updateDirection(){
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		Command[] c = toDirection(input);
		return c;
		}

	/** 
	 * It parses the input and return an array of Command.
	 * @param input
	 * @return an array of Command
	 */
	private static Command[] toDirection(String input){	
		int p = 0; //Current player.
		if(input.length() != 0){	
			switch(input.toUpperCase().charAt(input.length() - 1)){
				case 'W': if (arrow[p].getValue() != Direction.DOWN)  //If you are going down you can't turn up, or you will die. So this prevent.
						arrow[p].setDirection(Direction.UP);
						break;
				case 'A': if (arrow[p].getValue() != Direction.RIGHT)
						arrow[p].setDirection(Direction.LEFT);
						break;
				case 'S': if (arrow[p].getValue() != Direction.UP)
						arrow[p].setDirection(Direction.DOWN);
						break;
				case 'D': if (arrow[p].getValue() != Direction.LEFT)
						arrow[p].setDirection(Direction.RIGHT);
						break;
				}
			}
		Synchronizer.setLastButtonPressed(arrow);
		return arrow;
		}
	}
