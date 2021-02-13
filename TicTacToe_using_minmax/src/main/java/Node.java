/**
 * This class is used to store a state of a tic tac toe puzzle in the form of a string as well as a min/max value
 * Methods are included to set the min/max value depending on whose turn it is, X or O
 * @author Mark Hallenbeck
 *
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 *
 */

public class Node {
	
	private String[] state;
	
	private int minMaxValue;
	
	private int movedTo;
	
	Node(String[] stateOfPuzzle, int move)
	{
		state = stateOfPuzzle;
		
		movedTo = move;
		
		minMaxValue = -1;
	}
	
	int getMovedTo()
	{
		return movedTo;
	}
	
	/**
	 * checks for all the ways that O can win and sets minmax to -10. If it is a draw, sets it to 0
	 */
	void setMinMax_for_O()
	{
		
		if(checkForDraw())
			{
				minMaxValue = 0;
			}
		
		if(state[0].equals("o") && state[1].equals("o") && state[2].equals("o")) //horizontal top
		{
			minMaxValue = -10;
		}
		
		if(state[3].equals("o") && state[4].equals("o") && state[5].equals("o"))//horizontal middle
		{
			minMaxValue = -10;
		}

		if(state[6].equals("o") && state[7].equals("o") && state[8].equals("o"))//horizontal bottom
		{
			minMaxValue = -10;
		}

		if(state[0].equals("o") && state[3].equals("o") && state[6].equals("o"))//vert right
		{
			minMaxValue = -10;
		}

		if(state[1].equals("o") && state[4].equals("o") && state[7].equals("o"))//vert middle
		{
			minMaxValue = -10;
		}

		if(state[2].equals("o") && state[5].equals("o") && state[8].equals("o"))//vert left
		{
			minMaxValue = -10;
		}

		if(state[0].equals("o") && state[4].equals("o") && state[8].equals("o"))// diag from top left
		{
			minMaxValue = -10;
		}

		if(state[2].equals("o") && state[4].equals("o") && state[6].equals("o"))// diag from top right
		{
			minMaxValue = -10;
		}

	}
	
	/**
	 * checks for all the ways that X can win and sets minmax to 10. If a draw, sets minmax to 0
	 */
	void setMinMax_for_X()
	{
		if(checkForDraw())
		{
			minMaxValue = 0;
		}
		
		if(state[0].equals("x") && state[1].equals("x") && state[2].equals("x")) //horizontal top
		{
			minMaxValue = 10;
		}
		
		if(state[3].equals("x") && state[4].equals("x") && state[5].equals("x"))//horizontal middle
		{
			minMaxValue = 10;
		}

		if(state[6].equals("x") && state[7].equals("x") && state[8].equals("x"))//horizontal bottom
		{
			minMaxValue = 10;
		}

		if(state[0].equals("x") && state[3].equals("x") && state[6].equals("x"))//vert right
		{
			minMaxValue = 10;
		}

		if(state[1].equals("x") && state[4].equals("x") && state[7].equals("x"))//vert middle
		{
			minMaxValue = 10;
		}

		if(state[2].equals("x") && state[5].equals("x") && state[8].equals("x"))//vert left
		{
			minMaxValue = 10;
		}

		if(state[0].equals("x") && state[4].equals("x") && state[8].equals("x"))// diag from top left
		{
			minMaxValue = 10;
		}

		if(state[2].equals("x") && state[4].equals("x") && state[6].equals("x"))// diag from top right
		{
			minMaxValue = 10;
		}

	}

	void setMinMax(int x)
	{
		minMaxValue = x;
	}
	
	/**
	 * check the state to see if it is a draw (no b's in the string only X and O)
	 * @return true if its a draw, false if not
	 */
	boolean checkForDraw()
	{
		for(int x = 0; x < state.length; x++)
		{
			if(state[x].equals("*"))
			{
				return false;
			}
		}
		
		return true;
	}
	int getMinMax()
	{
		return minMaxValue;
	}
	
	String[] getInitStateString()
	{
		return state;
	}

}
