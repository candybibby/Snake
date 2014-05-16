

/**
 * Command class
 * Istances of this class represent a kind of command insert by users.
 * @author Italian People
 */


public abstract class Command{
	
	private int value;
	
	public Command(int value){
		this.value = value;
		}
	
	/** 
	 * @return command's value
	 */
	public int getValue(){
		return this.value;
		}
		
	/**
	 * Set value to this instance of Command.
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
		}

	}
