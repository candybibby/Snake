package tests;

import static org.junit.Assert.*;

import org.junit.*;
import package1.*;

public class TestFood {

	private Synchronizer sync;
	
	@Before
	public void initializeSync(){
		this.sync = new Synchronizer();
	}

	@Test
	public void TestConstructorParamChar(){
		Food f = new Food (sync, 'c');
		assertEquals("The food must be represanted by a C ",'c', f.getFoodElement());
	}
	
	
	@Test
	public void TestConstructorParamCoordinates(){
		Food f = new Food (sync, 10, 15);
		assertEquals(10, f.getX());
		assertEquals(15, f.getY());
		assertEquals(Food.FOOD_ELEMENT_DEFAULT, f.getFoodElement());
	}
	
	@Test
	public void TestConstructor3Param(){
		Food f = new Food (sync, 10, 15, Symbol.MOUSE);
		assertEquals(10, f.getX());
		assertEquals(15, f.getY());
		assertEquals(Symbol.MOUSE, f.getFoodElement());
	}
	
	
	@Test
	public void TestConstructorSynch(){
		Synchronizer synch = new Synchronizer();
		Food f = new Food(synch);
		assertEquals(synch, f.getSynchronizer());
	}
	
	@Test
	public void TestPlaceFoodPossible(){
		Food f = new Food(sync);
		int x = f.getX();
		int y = f.getY();
		
		boolean isEmpty = f.getSynchronizer().isEmpty(x, y);
		
		if (isEmpty){														// if the square isEmpty
			f.placeFood();														// we can use placeFood() with the coordinates (x, y)
			isEmpty = f.getSynchronizer().isEmpty(x, y);						// update of the boolean isEmpty, value FALSE (if the method works)
			assertEquals(isEmpty, false);										// check if (!isEmpty)
			char obtained = f.getSynchronizer().getGameWorld()[x][y];			// get the char obtained at the coordinates (x,y)
			assertEquals(f.getFoodElement(), obtained);							// check if the char obtained is the same as the food created
		}
		
		else {																// else
			f.placeFood();														// we execute placeFood() 
			x = f.getX();														// we get the coordinates in variables
			y = f.getY();														// because they changed
			isEmpty = f.getSynchronizer().isEmpty(x, y);
			assertEquals(isEmpty, false);
			char obtenu = f.getSynchronizer().getGameWorld()[x][y];
			assertEquals(f.getFoodElement(), obtenu);
		}
		
	}
	
	
}
