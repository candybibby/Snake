package tests;

import static org.junit.Assert.*;

import org.junit.*;

import package1.*;

public class TestWorld {

private Synchronizer sync;
	
	@Before
	public void initializeSync(){
		this.sync = new Synchronizer();
	}

	@Test
	public void testConstructor(){
		World world = new World(sync);
		assertEquals(sync, world.getFood()[0].getSynchronizer());
		assertEquals(sync, world.getSnake().getSynchronizer());
		assertEquals(sync, world.getDisplay().getSync());
		assertEquals(sync, world.getControl().getSynchronizer());
	}

	
	@Test
	public void testCreateNewGame(){
		World world = new World(sync);
		int lvl = 2;
		Obstacle obs = new Obstacle(lvl, sync);
		assertEquals(2, obs.getLevel());
		
	}
}
