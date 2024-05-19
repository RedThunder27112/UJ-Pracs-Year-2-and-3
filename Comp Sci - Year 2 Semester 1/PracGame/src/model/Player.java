package model;
/*
Start:
-Initialise PLayer
-Calculate Sum

Game Loop:
-Player enters sum

End State:
-PLayer runs out of tires
-PLayer enters correct sum

Game mechanics:
-Player types integer via input stream




*/
public class Player 
{
	private int numTriesRemaining;
	private int score;
	
	public Player(int numTriesRemaining)
	{
		score = 0;
		this.numTriesRemaining = numTriesRemaining;
	}
	
	public int getNumTriesRemaining()
	{
		return numTriesRemaining;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void decreaseTries()
	{
		numTriesRemaining--;
	}
	
	public void increaseScore()
	{
		score++;
	}
}
