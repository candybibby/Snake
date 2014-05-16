package tests;

import static org.junit.Assert.*;

import org.junit.*;

import package1.*;

public class TestObstacle {
	
	private Synchronizer sync;
	
	@Before
	public void initialize(){
		this.sync = new Synchronizer();
	}
	
	@Test
	public void testConstructorWrongLevel1(){
		Obstacle obs = new Obstacle(-1, sync);
		assertEquals(Obstacle.EASY, obs.getLevel());
	}
	
	
	@Test
	public void testConstructorWrongLevel2(){
		Obstacle obs = new Obstacle(4, sync);
		assertEquals(Obstacle.EASY, obs.getLevel());
	}
	
	@Test
	public void testConstructorLevel(){
		Obstacle obs = new Obstacle (2, sync);
		assertEquals(Obstacle.HARD, obs.getLevel());
	}
	
	
	@Test
	public void testConstructorSync(){
		Obstacle obs = new Obstacle (sync);
		assertEquals(sync, obs.getSynchronizer());
	}
	
	
	@Test
	public void testObstacleAroundArea() {
		//Test if the space near the edge of the area is empty
		
		Obstacle obs = new Obstacle(3,sync);
		
		int length1 = this.sync.getGameAreaHeight()-1;
		int length2 = this.sync.getGameAreaWidth()-1;
		
		for(int i = 1; i < length1; i++){
			for(int j = 1; j < length2;j++){
				boolean res = false;
				if ( (i == 1) || (i == length1) || (j == 1) || (j == length2)){
					res = this.sync.isEmpty(i, j);
					assertEquals(true, res);
				}
			}
		}
	}
	
	@Test
	public void testInitializeArea(){
		//Test if the arena is created
		
		Obstacle obs = new Obstacle(3,sync);
		
		int length1 = this.sync.getGameAreaHeight();
		int length2 = this.sync.getGameAreaWidth();
		
		for(int i = 0; i < length1; i++){
			for(int j = 0; j < length2;j++){
				boolean res = false;
				if ( (i == 0) || (i == length1) || (j == 0) || (j == length2)){
					res = this.sync.isEmpty(i, j);
					assertEquals(false, res);
				}
			}
		}
	}
	
	
	

}
