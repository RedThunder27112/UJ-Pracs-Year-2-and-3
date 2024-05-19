package thread;

import java.util.Random;

public class GenerateRandTask implements Runnable
{
	private int ID;
	
	public GenerateRandTask(int ID)
	{
		this.ID = ID;
	}

	@Override
	public void run() 
	{
		Random random = new Random();
		int randInt = random.nextInt(100)+1;
		
		/*try {
			//Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.format("Task: %d, Number: %d%n", ID, randInt);
		//System.out.println("Task: "+ ID + " " + randInt);

	}

}
