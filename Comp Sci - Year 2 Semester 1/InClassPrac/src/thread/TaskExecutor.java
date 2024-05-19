package thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor implements Runnable 
{
	ArrayList<GenerateRandTask> tasks;

	
	public TaskExecutor(ArrayList<GenerateRandTask> tasks)
	{
		this.tasks = tasks;
	}
	
	
	@Override
	public void run() 
	{
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		for(GenerateRandTask task : tasks)
		{
			threadPool.execute(task);
		}
		threadPool.shutdown();
		
		while(!threadPool.isTerminated())
		{
			//wait
		}
		System.err.println("Threadpool has shut down");
		

	}

}
