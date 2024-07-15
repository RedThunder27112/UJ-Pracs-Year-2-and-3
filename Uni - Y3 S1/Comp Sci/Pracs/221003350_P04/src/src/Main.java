import java.util.Scanner;
import java.util.StringTokenizer;
/*
 * Total Marks Main Class: 25
 * Compilation & Correct Execution Marks: 10
 */
public class Main {
	static LinkedQueue<Transaction> buyQueue = new LinkedQueue<Transaction>();
	static LinkedQueue<Transaction> sellQueue = new LinkedQueue<Transaction>();
	static int totalGain = 0;
	
	/**
	 * Process queue of transactions - determine if each transaction is a buy or sell
	 * transaction & add it to the appropriate queue
	 * @param transactions - a queue of buy and sell transactions
	 * 15 marks
	 */
	public static void processTransactions(LinkedQueue<String> transactions) 
	{
		
		//check if empty
		if(transactions.isEmpty())
		{
			return;
			
		}
		
		//get size for loop
		int size = transactions.size();
		
		for(int i = 0; i < size; i++)
		{
			String line = transactions.dequeue();
			StringTokenizer st = new StringTokenizer(line);
			
			//break string into relevant tokens
			String text = st.nextToken();
			String buyOrSell = st.nextToken();
			int quantity = Integer.parseInt(st.nextToken());
			int unitPrice = Integer.parseInt(st.nextToken());
			
			//check if transaction is a buy or sell, then add to relevent queue
			if(buyOrSell.equals("BUY"))
			{
				//create new transaction and add to buy queue
				Transaction t = new Transaction(quantity,unitPrice);
				buyQueue.enqueue(t);
				
			}else
			if(buyOrSell.equals("SELL"))
			{
				//create new transaction and add to buy queue
				Transaction t = new Transaction(quantity,unitPrice);
				sellQueue.enqueue(t);		
				
			}else
			{
				return;
			}
			
		}

	}
	
	/**
	 * Calculate capital gain(loss)
	 * @return totalGain
	 * 10 marks
	 */
	public static Integer calculateCapitalGainLoss() 
	{
	
		//check how many sell you want
		if(sellQueue.isEmpty())
		{
			return totalGain;
		}
		
		//get size for loop
		int sizeS = sellQueue.size();
		int sizeB = buyQueue.size();
		
		for(int i = 0; i < sizeS; i++)
		{
			//get each sell transaction
			Transaction sellT = sellQueue.dequeue();
			int quantityS = sellT.quantity;
			int priceS = sellT.unitPrice;
			
			//if nothing was bought to sell, return what was cal
			if(buyQueue.isEmpty())
			{
				return totalGain;
			}else
			{
				//else sell as much as you can
				boolean flag = true;
				
				while(flag)
				{
					//get a buy transaction
					Transaction buyT = buyQueue.dequeue();
					
					//check if list empty
					if(buyQueue.isEmpty())
					{
						flag = false;
					}
					
					int quantityB = buyT.quantity;
					int priceB = buyT.unitPrice;
					//check if you want to sell all of a buy transaction, or only part
					if(quantityB < quantityS)
					{
						//sell all of that buy transaction
						totalGain += quantityB*priceS - quantityB*priceB;
						quantityS = quantityS - quantityB;
					}else
					{
						//sell only a portion of the buy transaction
						totalGain += quantityS*priceS - quantityS*priceB;
						buyT.quantity = quantityB - quantityS;
						//put leftover back into queue
						buyQueue.enqueue(buyT);
						flag = false;
					}
				}
			}
		}
		return totalGain;
	}
	
	public static void main(String[] args) {
		String response = "";
		Scanner s = new Scanner(System.in);
		LinkedQueue<String> instructionQueue = new LinkedQueue<String>();
		Integer capGainLoss;
		
		while (!response.toLowerCase().equals("quit")){
			System.out.println("Select option: ");
			System.out.println("1) Enter new transaction");
			System.out.println("2) Calculate capital gain or loss");
			System.out.println("or \"quit\" to quit.");
			response = s.nextLine();
			
			switch(response.toLowerCase()){
				case "1": {
					System.out.println("Enter transaction:");
					response = s.nextLine();
					if (!response.equals(""))
						instructionQueue.enqueue(response);
				}
					break;
				case "2": {
					processTransactions(instructionQueue);
					capGainLoss = calculateCapitalGainLoss();
					if (capGainLoss == null)
						System.out.println("Unmatched sell transaction(s).");
					else
						System.out.println("Capital Gain/Loss: "+capGainLoss);
				}
					break;
				case "quit": break;
				default: System.out.println("Incorrect option selected. Please try again.\r\n eg Format:  Transaction: SELL 100 20\r\n");
			}			
		}
	}
}