package tests;

import static org.junit.Assert.*;
import org.junit.*;
import package1.*;

public class TestControl {

	private Synchronizer sync;
	private Control control;
	
	@Before
	public void initialize(){
		this.sync = new Synchronizer();
		this.control = new Control(sync);
	}
	
	@Test
	public void testArrowDirection() {
		
		//assertEquals('UP', sync.getLastButtonPressed());
	}

}
