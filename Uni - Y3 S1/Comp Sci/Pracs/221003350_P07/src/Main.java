//Overall Main class: 25 marks ***********************************************
//Correctness (15 marks) ********************************
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	/*
	 * Reads all the current restaurants from the local database (binary file of a collection of restaurants)
	 * calculates the its distance from it currentLocation and builds a
	 * PriorityQueue where Entries contain distances from the restaurant as a key and 
	 * the restaurant object as the value.
	 * @param The path to the database and the current location of the user 
	 * 
	 */
	public static PriorityQueue<Double,Restaurant> calculateDistanceFromDB(String path, GPSPoint currentLocation){
		PriorityQueue<Double,Restaurant> restaurants = new PriorityQueue<Double,Restaurant>();
		File f = new File(path);
		ObjectInputStream ois = null;		
		
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			Restaurant r = null;
			while(true){
				try
				{
					r = (Restaurant)ois.readObject();
					System.out.println("Inserting: "+r.getName());
					Double distance = currentLocation.calculateDistance(r.getLocation());					
					if (r!= null)
						restaurants.insert(new Entry(distance, r));				
				}
				catch(EOFException e){
					break;
				}
			}			
		} catch (FileNotFoundException e) {
			System.err.println("File not found, please check path");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An error occured during reading from database");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Restaurant not recognized");
			e.printStackTrace();
		}
		finally{
			try {
				ois.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
		return restaurants;
	}
	
	/*
	 * A function that takes in the Priority Queue of restaurants with the key being the distance from your current location
	 * and does the following:
	 * - Creates a new Priority Queue with an Integer rating as the key and Restaurant object as the value
	 * - In a loop that covers every Entry in the passed PriorityQueue:
	 * + Remove from the passed queue
	 * + Display on the command line the current restaurant details
	 * + Prompt the User from the command line for a rating between 1 and 5
	 * + Add new entry into new Priority Queue   
	 * ********** 8 marks ****************************
	 */
	
	public static PriorityQueue<Integer,Restaurant> rateRestaurants(PriorityQueue<Double,Restaurant> restaurants){
		//TODO: Complete the rate Restaurants function
		 // - Creates a new Priority Queue with an Integer rating as the key and Restaurant object as the value
		PriorityQueue<Integer,Restaurant> newQueue = new PriorityQueue<Integer,Restaurant>();

		 // - In a loop that covers every Entry in the passed PriorityQueue:
		PositionListIterator<Entry<Double,Restaurant>> iterator = restaurants.entries();
		
		Scanner sc = new Scanner(System.in);

		while(iterator.hasNext())
		{
			Entry<Double,Restaurant> position = iterator.next();
			
			// + Remove from the passed queue
			Entry<Double,Restaurant> current = restaurants.removeMin();
			
			
			
			if(current != null)
			{
				if(current.getKey() != null)
				{
					// + Display on the command line the current restaurant details
					System.out.println(current.toString());
					
					 // + Prompt the User from the command line for a rating between 1 and 5
					System.out.println("Enter a rating between 1 and 5:");
					
					 // + Add new entry into new Priority Queue  
					Entry<Integer, Restaurant> newEntry = new Entry<Integer, Restaurant>(sc.nextInt(),current.getValue());
					newQueue.insert(newEntry);
				}		
			}
		}
		
		return newQueue;
	}
	
	/*
	 * A function that takes in the Priority Queue of rated restaurants and saves them to a binary file with the specified path
	 * ********** 7 marks ****************************
	 */
	public static void saveRatedRestaurants(PriorityQueue<Integer,Restaurant> ratedRestaurants, String path) 
	{
		File file = new File(path);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path))))
		{
			
			PositionListIterator<Entry<Integer,Restaurant>> iterator = ratedRestaurants.entries();
			

			while(iterator.hasNext())
			{
				Entry<Integer,Restaurant> current = iterator.next();
				
				if(current != null)
				{
					if(current.getKey() != null)
					{				
						oos.writeObject(current);
					}		
				}
				
			}
			
		}catch (EOFException e) 
		{
			
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace()
			;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Default location in case something goes wrong with input or that you can use to test
		GPSPoint location  = new GPSPoint(27.998614,-26.183000);
		
		System.out.println("Enter current longitude:");
		location.setLong(sc.nextDouble());
		System.out.println("Enter current latitude:");
		location.setLat(sc.nextDouble());
		
		System.out.println("Building restaurant index");		
		PriorityQueue<Double,Restaurant> restaurantDB = calculateDistanceFromDB("restaurant.db",location);
		
		System.out.println("Testing PriorityQueue insert");
		Restaurant r1 = new Restaurant("Student Center","Your go to place.", new GPSPoint(27.998614,-26.183000));
		restaurantDB.insert(new Entry<>(location.calculateDistance(r1.getLocation()), r1));
		System.out.println(r1.getName()+" added");
		
		Restaurant r2 = new Restaurant("Chaf Pozi","A good old shisanyama place.", new GPSPoint(28.002175,-26.181158));
		restaurantDB.insert(new Entry<>(location.calculateDistance(r2.getLocation()), r2));
		System.out.println(r2.getName()+" added");
				
		System.out.println("Testing PriorityQueue display (please note that this is not in the order that will follow next)");
		System.out.println("There are "+restaurantDB.size()+" restaurants and you should visit them in this order: ");
		int i=0;
		Iterator<Entry<Double, Restaurant>> iter = restaurantDB.entries();
		while(iter.hasNext()){
			i++;
			Entry<Double, Restaurant> nextRestaurant = iter.next();
			System.out.println(i+": "+nextRestaurant.getValue());
						
		}
		System.out.println("Testing Restaurant ratings from closest");
		saveRatedRestaurants(rateRestaurants(restaurantDB),"RatedRestaurants.db");
		
		System.out.println("Your restaurant experience is now complete");	
		
	}
}