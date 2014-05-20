package package1;

/**
 * Symbol class.
 * This is a sort of public dictionary which contains the char we are writing in the matrix.
 * Please in your code use the name (like Symbol.MOUSE) instead of a constant (like 'm')
 * Feel free to correct it and add new symbols when you need them!
 *	@author Italian People
 */

public class Symbol{
	public static final char EMPTY = '\0'; //empty cell, where Snake and food can be placed
	public static final char MOUSE = 'm';  //default food element. Stands for Mouse
	public static final char[] HEAD =  {'S', 'T'};   // Snake's head
	public static final char[] BODY = {'o','e'};   //Snake's body
	public static final char WALL = 'X';   //wall element
}
