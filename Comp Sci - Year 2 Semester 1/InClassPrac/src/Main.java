import java.util.ArrayList;

import thread.GenerateRandTask;
import thread.TaskExecutor;

public class Main//multi threading
{

	public static void main(String[] args) 
	{
		ArrayList<GenerateRandTask> tasks = new ArrayList<GenerateRandTask>();
		
		for(int i = 0; i < 10; i++)
		{
			tasks.add(new GenerateRandTask(i));
		}
		TaskExecutor threadPoolExecutor = new TaskExecutor(tasks);
		
		Thread thread = new Thread(threadPoolExecutor);
		thread.start();
		

		try {
			thread.join();
			System.out.println("-------------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Main method done");
		
		
		
		
		


	}

}
