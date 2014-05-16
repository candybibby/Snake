package tests;

import static org.junit.Assert.*;
import package1.*;

import org.junit.Test;
import java.io.*;

public class TestSynchronizer {

	@Test
	public void testEmptyConstructor(){
		Synchronizer sync = new Synchronizer();
		assertEquals(Synchronizer.LENGTH, sync.getGameAreaHeight());
		assertEquals(Synchronizer.LENGTH, sync.getGameAreaWidth());
		assertEquals(Synchronizer.LENGTH, sync.getGameWorld().length);
		assertEquals(false, sync.isGameOver());
		assertEquals(false, sync.isStopGameLoop());
		assertEquals(false, sync.isGameLoopRunning());
		assertEquals(true, sync.getSnakeStillAlive());
		assertEquals(Synchronizer.MAX_PLAYER_NUMBER, sync.getScores().length);
		assertEquals(false, sync.isFoodPresent(5));
		
	}

	
	@Test
	public void testConstructorWrongLenght(){
		int lenght = 2;
		Synchronizer sync = new Synchronizer(lenght);
		assertEquals(Synchronizer.LENGTH, sync.getGameAreaHeight());
		assertEquals(Synchronizer.LENGTH, sync.getGameAreaWidth());
		assertEquals(Synchronizer.LENGTH, sync.getGameWorld().length);
		assertEquals(false, sync.isGameOver());
		assertEquals(false, sync.isStopGameLoop());
		assertEquals(true, sync.getSnakeStillAlive());
	}
	

	@Test
	public void testConstructorLenght(){
		int lenght = 15;
		Synchronizer sync = new Synchronizer(lenght);
		assertEquals(lenght, sync.getGameAreaHeight());
		assertEquals(lenght, sync.getGameAreaWidth());
		assertEquals(lenght, sync.getGameWorld().length);
		assertEquals(false, sync.isGameOver());
		assertEquals(false, sync.isStopGameLoop());
		assertEquals(true, sync.getSnakeStillAlive());
	}
	
	@Test
	public void testWriteThisCell(){
		Synchronizer sync = new Synchronizer();
		sync.writeThisCell(5, 5, 'c');
		assertEquals('c', sync.getGameWorld()[5][5]);
	}
	
	@Test
	public void testTheGameIsOver(){
		Synchronizer sync = new Synchronizer();
		assertEquals(false, sync.isGameOver());
		sync.theGameIsOver();
		assertEquals(true, sync.isGameOver());
	}
	
	@Test
	public void testIsEmpty(){
		Synchronizer sync = new Synchronizer();
		sync.writeThisCell(5, 5, '\0');
		assertEquals(true, sync.isEmpty(5, 5));
		sync.writeThisCell(5, 5, 'c');
		assertEquals(false, sync.isEmpty(5, 5));
	}
	
	
	@Test
	public void test_SaveHighScore_initializeHighScore() throws IOException{
		Synchronizer sync = new Synchronizer();
		String file = "Testfile.txt";
		assertEquals(0, sync.highScore);
		sync.setHighScore(7);
		sync.saveHighScore(file);
		sync.initializeHighScore(file);
		assertEquals(7, sync.highScore);
	}

}
