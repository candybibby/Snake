package package1;

/**
 * Direction class.
 * This class represents certain type of input: a direction. There are 4 static constants which
 * represent commons directions ( UP, DOWN, LEFT, RIGHT) easily implemented with an integer value.
 *	@author Italian People
 */

public class Direction extends Command {
	/**
	 * Assigns a direction to a corresponding value
	 */
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	/**
	 * Constructor with 1 parameter.
	 * Create a Direction with this value.
	 * @param value
	 */

	public Direction(int value)                 
	{
		super(value);
        }
	
	/**
	 * Set specified value to this Direction.
	 * @param value
	 */
	public void setDirection (int value)
	{
		super.setValue (value);
	}
	

	public String toString()                 //Method used to debug this class
	{                                        
		switch(this.getValue())
		{
			case 0: return "UP ";
			case 1: return "DOWN ";
			case 2: return "LEFT ";
			case 3: return "RIGHT ";
			default: return "" + this.getValue();
		}
	}

}
		
