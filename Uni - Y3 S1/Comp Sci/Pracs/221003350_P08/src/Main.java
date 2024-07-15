import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

//Overall correctness: 10 marks **********************************************************************
//Overall for Main class: 20 marks *******************************************************************
public class Main {
	/*
	 * Method that reads from the selected text file and returns a PositionList of keyword tokens  
	 * 10 marks ***********************************************************************************
	 */
	public static PositionList<String> readTokens(String path){
		
		File file = new File("src",path);
		
		PositionList<String> positionList = new PositionList<String>();
		try(Scanner sc = new Scanner(file))
		{
			while(sc.hasNext())
			{
				String line = sc.nextLine();
				//token.replaceAll("1", "2");

				StringTokenizer st = new StringTokenizer(line);
				
				while(st.hasMoreTokens())
				{
					String token = st.nextToken();
			
					positionList.addLast(token.toLowerCase());
				}
			}

			
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return positionList;
	}
	
	/*
	 *  Builds a heap of entries (where the key is the term frequency and the value is the respective keyword)
	 *  10 marks ***********************************************************************************
	 */
	public static Heap<Integer,String> buildHeap(PositionList<String> tokens)
	{
		Heap<Integer,String> heap = new Heap<Integer,String>();
		for(String s : tokens)
		{
			int value = 0;
			
			for(String ss : tokens)
			{
				if(s.equals(ss))
				{
					value++;
					
				}
			}

			heap.insert(value, s);

			//once counted, remove al of that value from the string
			Position<String> p;
			while((p = tokens.search(s)) != null)
			{
				tokens.remove(p);
			}
		}


		//TODO: Implement a method that builds a heap of entries (where the key is the term frequency and the value is the respective keyword)
		return heap;
	}
	
	/*
	 * Main method which adds all the keywords read from the text file to a PositionList, adds it to a Heap based on term frequency and 
	 * removes the keywords in order.   
	 * 
	 */
	public static void main(String[] args) {
		/*
		 * They can provide an implementation, use your discretion
		 */
		PositionList<String> tokens  = readTokens("computer_science.txt");
		
		System.out.println("Current tokens:");
		System.out.println(tokens);
		
		
		System.out.println("=======================================================================================");
		
		Heap<Integer,String> heap = buildHeap(tokens);
		
		
		
		System.out.println("Frequent tokens:");

		while(!heap.isEmpty()){		
			
			System.out.println(heap.removeMin().getValue()+ " removed");

		}
		
		System.out.println("=======================================================================================");
		
	}
	
}
