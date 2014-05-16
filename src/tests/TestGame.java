package tests;

import static org.junit.Assert.*;
import org.junit.*;
import package1.*;

public class TestGame {
	
	private Game game;
	
	@Before
	public void initialize(){
		game = new Game(new Synchronizer());
	}

	@Test
	public void checkLevelNewGameEasy(){
		game.runNewGame(0);
		World w = game.getWorld();
		Obstacle ob = w.getObstacle();
		assertEquals(0, ob.getLevel());		
	}

	@Test
	public void checkLevelNewGameHard(){
		game.runNewGame(2);
		World world = game.getWorld();
		Obstacle obstacle = world.getObstacle();
		assertEquals(2, obstacle.getLevel());
	}
}
