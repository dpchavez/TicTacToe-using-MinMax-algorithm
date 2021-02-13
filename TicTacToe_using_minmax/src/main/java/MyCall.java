
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/*
 * Daniela Chavez
 * CS342
 * UIC Fall2020(Corona Time)
 * Project 4
 * */

public class MyCall implements Callable<Integer>{

	Board dummyBoard = new Board();
	String[] board;
	Integer player;
	String LevelX;
	String LevelO;
	Integer firstMove = 0;
	
	MyCall(String[] game, Integer player, String playerLevelX, String playerLevelO, int counter){
		LevelX = playerLevelX;
		LevelO = playerLevelO;
		board = game;
		this.player = player;
		firstMove = counter;
	}
	
	//-----------------------------------------------------------------------------------------
	//This function is here to determine which is the best move for X
	Node BestMoves(ArrayList<Node>Moves, String[] board) 
	{
		
		Node BestMove = Moves.get(0);
		//BestMove.setMinMax(-20);
		
		for(int i = 0; i < Moves.size(); i++)
		{
			
			Node temp = Moves.get(i);
			//System.out.print("->"+temp.getMinMax());
			if(temp.getMinMax() > BestMove.getMinMax())
			{
				BestMove = temp;
			}
			
		}
		
		//System.out.println("-> ..." + BestMove.getMovedTo());
		return BestMove;
	}

	//---------------------------------------------------------------------------------------
	int BegginerLevel(String[] board) 
	{
		Integer val = 0;
		boolean CheckBoard = true;
		

		while(CheckBoard) {
			
			Random r = new Random();
			val = r.nextInt(9);
			
			if(board[val] == "x" || board[val] == "o") 
				CheckBoard = true;
					
			else 
				CheckBoard = false;
			}
		
		return val;
	}
	//---------------------------------------------------------------------------------------
	
	int IntermediateLevel(String[] board) 
	{
		Integer val = 0;
		boolean CheckBoard = true;
		
		
		if (player == 1) 
		{
			
			while(CheckBoard) 
			{
				Random r = new Random();
				val = r.nextInt(9);
				
				if(board[val] == "x" || board[val] == "o") 
					CheckBoard = true;
					
				else 
					CheckBoard = false;
			}
			
		}
		
		else 
		{
			
			MinMax gameLogic = new MinMax(board);
			//This is the array list that is looking for the moves
			ArrayList<Node>OMoves = gameLogic.findMoves();
			Node O = BestMoves(OMoves, board);
			
			val = O.getMovedTo() -1;
			
			
		}
		
		
		
		
		return val;
	}
	//---------------------------------------------------------------------------------------
	int ExpertLevel(String[] board) 
	{
		int val = 0;
		
		if(player == 1 && firstMove == 0) 
		{
			
			Random r = new Random();
			val = r.nextInt(9);
			return val;
			
			
		}
		
		else {
		
		if (player == 1 && firstMove != 0) {
			MinMax gameLogic = new MinMax(board);
			//This is the array list that is looking for the moves
			ArrayList<Node>XMoves = gameLogic.findMoves();
			Node X = BestMoves(XMoves, board);
			val = X.getMovedTo() -1;
		}
		
		else
		{

			
			dummyBoard.board = board;
			
			dummyBoard.FlipBoard();
			
			
			MinMax gameLogic = new MinMax(dummyBoard.board);
			//This is the array list that is looking for the moves
			ArrayList<Node>OMoves = gameLogic.findMoves();
			Node O = BestMoves(OMoves, dummyBoard.board);
			val = O.getMovedTo() -1;
			
			dummyBoard.FlipBoard();
			
		}
		}
		
		return val;
	}
	
	//---------------------------------------------------------------------------------------
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		//boolean bool = true;
		Integer val = 0;
		
		if (player == 1) 
		{
			if (LevelX == "Novice")
				val = BegginerLevel(board);

			else if (LevelX == "Advanced")
				val = IntermediateLevel(board);
			else
				val = ExpertLevel(board);
		}
		
		else 
		{
			if (LevelO == "Novice")
				val = BegginerLevel(board);

			else if (LevelO == "Advanced")
				val = IntermediateLevel(board);
			else
				val = ExpertLevel(board);
			
		}

		
		//Thread.sleep(1000);
		//System.out.println("\n" + "player: " + player + " chooses index: "+val);
		return val;
		
	}
	
}
	
