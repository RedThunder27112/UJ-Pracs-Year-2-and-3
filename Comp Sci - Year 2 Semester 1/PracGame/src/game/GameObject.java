package game;

import java.util.Random;
import java.util.Scanner;

import model.Player;

public class GameObject implements Runnable
{
	private Player player;
	private int sum;
	private int lhs, rhs;
	
	public GameObject(int numTries)
	{
		player = new Player(numTries);
		calcRandSum();
	}

	private void calcRandSum() 
	{
		Random random = new Random();
		int lhs = random.nextInt(501);
		int rhs = random.nextInt(501);
		
		sum = lhs + rhs;
	}

	@Override
	public void run() 
	{
		boolean endReached = false;
		
		//while end state not reached, continue game
		do
		{
			
			System.out.println("Enter the sum of :" +lhs+" + "+rhs);
			Scanner sc = new Scanner(System.in);
			int userSum = Integer.parseInt(sc.nextLine());
			
			if(userSum == sum)
			{
				System.err.println("YOu win!");
				endReached = true;
			}else
			{	
				player.decreaseTries();
				if(player.getNumTriesRemaining() == 0)
				{
					System.err.println("You lose");
					endReached = true;
				}else
				{
					System.err.println("Wrong answer, try again!");
					
				}
				
			}
			
		}while(!endReached);
		
	}
	
	
	
	

}
