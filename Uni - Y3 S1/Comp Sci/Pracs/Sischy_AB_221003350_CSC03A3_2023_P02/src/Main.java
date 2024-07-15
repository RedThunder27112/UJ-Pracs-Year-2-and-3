


/*
 * Total Marks Main Class: 14
 * Compilation & Correct Execution Marks: 10
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import acsse.*;
//import acsse.ProductItem;
//import acsse.SList;anner;

public class Main {
	private static String path = "list.dat";	
	
	/*
	 * Appends a new ProductItem to the current binary file
	 * 7 Marks
	 */
	public static void writeProductItemToFile(ProductItem item)
	{
		//gets old list and adds new item to it
		//SList<ProductItem> oldList = new SList<ProductItem>();
		SList<ProductItem> oldList = readProductItemsFromFile();
		
		if(oldList.equals(null))
		{
			oldList.insertFirst(item);
		}else
		{
			oldList.insertLast(item);
		}
			
		
		File file = new File("data",path);
		
		//open binary file
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file))))
		{
			oos.writeInt(oldList.getSize());
			

			Node<ProductItem> tempProduct = oldList.first();
			
			
			for(int i = 0; i < oldList.getSize(); i++)
			{
				oos.writeObject(tempProduct.getElement());
				tempProduct = tempProduct.getNext();
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: Complete
	}
	
	/*
	 * Reads all the objects in the current binary file and loads them into a Single Linked List (SList)
	 * 7 Marks
	 */
	public static SList<ProductItem> readProductItemsFromFile()
	{
		SList<ProductItem> productList = new SList<ProductItem>();
		//reads 
		File file = new File("data",path);
		
		//open binary file
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))))
		{
			//get number of products in list
			int numProduct = ois.readInt();
			
			
			ProductItem product = null;
			try 
			{
				product = (ProductItem)ois.readObject();
				

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
					
			productList.insertFirst(product);
			Node<ProductItem> currentNode = productList.first();
			//productList.addFirst((ProductItem)ois.readObject());
			
			for(int i = 0; i < numProduct-1; i++)
			{
				try 
				{
					product = (ProductItem)ois.readObject();
					

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
				
				//check if last element is being added
				if(i == (numProduct-2))
				{
					productList.insertLast(product);
				}else
				{
					productList.insertAfter(currentNode, product);
				}
				

			}
			
			
			
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return productList;
		
	}
	
	public static void main(String[] args) {		
		String response = "";
		Scanner s = new Scanner(System.in);
		while (!response.toLowerCase().equals("quit")){
			System.out.println("==================================================");
			System.out.println("===\t\tCommand Line TODO\t\t==");
			System.out.println("==================================================\n");
			System.out.println("The current Todo List path is: "+path);
			System.out.println("Select option: ");
			System.out.println("1) Set path");
			System.out.println("2) Read and Display current Todo List");
			System.out.println("3) Write new Todo item to current Todo List");
			System.out.println("or \"quit\" to quit.");		
			response = s.nextLine();
			
			switch(response.toLowerCase()){
				case "1": {
					System.out.println("Enter path:");
					path = s.nextLine();
				}
					break;
				case "2": {
					SList<ProductItem> list = readProductItemsFromFile();					
					System.out.println(list);
				
				}
					break;
				case "3":{
					try{
						ProductItem pi = new ProductItem();
						System.out.println("Enter ProductItem's id: ");
						response =s.nextLine();
						pi.setId(Integer.parseInt(response));
						System.out.println("Enter ProductItem's name: ");
						response = s.nextLine();
						pi.setName(response);
						System.out.println("Enter ProductItem's priority: ");
						response = s.nextLine();
						pi.setPrice(Double.parseDouble(response));;
						System.out.println("Enter ProductItem's description: ");
						response = s.nextLine();
						pi.setDescription(response);
						
						writeProductItemToFile(pi);
						System.out.println("Item added successfully");
						
					}
					catch(NumberFormatException nfe){
						System.err.println("Incorrect input inserted.");
					}					
				}
					break;
				case "quit":
					break;
				default: System.out.println("Incorrect option selected. Please try again.");
			}			
		}
		s.close();
	}
}