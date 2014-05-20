package package1;
/**
 * Control Class.
 * This class is useful to manage Input user interaction.
 * @author Italian People
 */

import java.awt.event.*;

public class Control implements KeyListener {


    //Default direction. When the game starts, Snake goes right (like Nokia 3310 :))
    static Direction[] arrow = {new Direction(Direction.RIGHT)};

    /**
     * Constructor with 1 parameter
     * Create an instance of Control class with the specified Synchronizer and
     * set a default Direction to each player.
     *
     * @param sync
     */

    public Control() {

        //arrow = new Direction [sync.getPlayerNumber()]();
        for (int i = 0; i < arrow.length; i++)
            arrow[i] = new Direction(Direction.RIGHT);

    }


    /**
     * It parses the input and return an array of Command.
     * @param e
     * @return an array of Command
     *
     */
    public void keyPressed(KeyEvent e) {
        int p = 0; //Current player.
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (arrow[p].getValue() != Direction.DOWN)  //If you are going down you can't turn up, or you will die.
                    arrow[p].setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (arrow[p].getValue() != Direction.UP)
                    arrow[p].setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (arrow[p].getValue() != Direction.RIGHT)
                    arrow[p].setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (arrow[p].getValue() != Direction.LEFT)
                    arrow[p].setDirection(Direction.RIGHT);
                break;
        }
        //Synchronizer.setLastButtonPressed(arrow);
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
	 * @return the sync
	 */
	/*public Synchronizer getSynchronizer() {
		return this.sync;
	}*/

}
