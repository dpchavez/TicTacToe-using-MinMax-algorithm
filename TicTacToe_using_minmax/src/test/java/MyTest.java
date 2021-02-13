import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/*
 * Daniela Chavez
 * CS342
 * UIC Fall2020(Corona Time)
 * Project 4
 * */
class MyTest {

	@Test
	void test1() {
		Board testBoard =  new Board();
		String[] test = {"x","x","x","x","x","x","x","x","x"};
		String[] textO = {"o","o","o","o","o","o","o","o","o"};
		testBoard.board = test;
		testBoard.FlipBoard();
		assertArrayEquals(textO, testBoard.board, "error ewwww");
	}
	
	@Test
	void test2() 
	{
		Board testBoard = new Board();
		String[] test = {"x","*","o","*","x","*","o","*", "x"};
		testBoard.board = test;
		assertTrue(testBoard.DidXWin(), "ewwww");
	}
	@Test
	void test3() 
	{
		Board testBoard = new Board();
		String[] test = {"o","*","x","*","o","*","x","*", "o"};
		testBoard.board = test;
		assertTrue(testBoard.DidOWin(), "ewwww");
	}
	
	@Test
	void test4() 
	{
		int index = 0;
		ExecutorService X = Executors.newFixedThreadPool(4);
		try {
			String[] test = {"x","*","o","*","x","*","o","*", "*"};
			Future<Integer> future1 = X.submit(new MyCall(test, 1, "Expert", "Novice",5));
		
			index = future1.get();
			assertEquals(1, index, "ewwwwww");
		}catch(Exception e){System.out.println(e.getMessage());}
		X.shutdown();

	}

	@Test
	void test5() 
	{
		int index = 0;
		ExecutorService X = Executors.newFixedThreadPool(4);
		try {
			String[] test = {"o","o","x","*","o","*","x","*", "o"};
			Future<Integer> future1 = X.submit(new MyCall(test, 0, "Expert", "Expert",5));
		
			index = future1.get();
			assertEquals(3, index, "ewwwwww");
		}catch(Exception e){System.out.println(e.getMessage());}
		X.shutdown();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
