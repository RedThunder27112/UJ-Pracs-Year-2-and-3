//************ Total Marks for correctness: 10 marks
//************ Total Marks for Main: 15 marks
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Main {	
	/*
	 * Reads all the current Petrol Stations from the local database (binary file of a collection of Petrol Stations)
	 * @param The path to the database 
	 * ********** 10 marks ****************************
	 */
	public static ArrayList<PetrolStation> readPetrolStationsFromDB(String path)
	{
		File file = new File(path);
		ArrayList<PetrolStation> arrayList = new ArrayList<PetrolStation>();
		int i = 0;
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))))
		{
			PetrolStation petrol;
			while((petrol = (PetrolStation)ois.readObject()) != null)
			{
				arrayList.add(i, petrol);
			}
			

		}catch (EOFException e) 
		{
			//end of file
		}catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayList;
	}	
		
	/*
	 * Calculates the closest petrol station to your current location
	 * @param the list of petrol stations and your current location
	 * @return the PetrolStation that is closest
	 * ********** 5 marks ****************************
	 */
	public static PetrolStation calculateClosestPetrolStation(ArrayList<PetrolStation> list, GPSPoint currentLocation ){
		
		Iterator<PetrolStation> iterator = list.iterator();
		double minDist = 99999999;
		PetrolStation closePetrol = null;

		while(iterator.hasNext())
		{
			//next petrol station iterator
			PetrolStation petrol = iterator.next();
			
			//distance calc
			double dist = petrol.getLocation().calculateDistance(currentLocation);
			
			//get min distance
			if(dist < minDist)
			{
				minDist = dist;
				closePetrol = petrol;
			}
		}

		return closePetrol;
	}
	
	public static void main(String[] args) {		
		ArrayList<PetrolStation> list = readPetrolStationsFromDB("petrolstations.db");
		
		PetrolStation p1 = new PetrolStation("Shell Boskruin Motors","Features: Vida e Cafe; Debonairs Pizza", new GPSPoint(27.959148,-26.091459));
		list.add(0, p1);
		
		PetrolStation p2 = new PetrolStation("Sasol Randburg","Features: Absa ATM ", new GPSPoint(27.990038,-26.110212));
		list.add(0, p2);
		
		//the following code is used to test if the remove function is working
		/*
		System.out.println();
		System.out.println();
		int i = 0;
		for(PetrolStation p : list)
		{
			i++;
			System.out.println(i+": "+p);
		}
		System.out.println();
		System.out.println();
		list.remove(11);
		list.remove(5);
		list.remove(0);
		i = 0;
		for(PetrolStation p : list)
		{
			i++;
			System.out.println(i+": "+p);
		}
		
		System.out.println();
		System.out.println();
		*/
		
		System.out.println(list.size()+" petrol stations loaded");		
		Scanner sc = new Scanner(System.in);
		String gps = "";
		while(!gps.toLowerCase().equals("q")){
			System.out.println("Enter your GPS coordinates in the long:lat format ('q' to quit): ");
			gps = sc.nextLine();
			if(gps.toLowerCase().equals("q"))
				break;
			Double longitude = Double.parseDouble(gps.split(":")[0]);
			Double latitude = Double.parseDouble(gps.split(":")[1]);
			GPSPoint currentLocation = new GPSPoint(longitude,latitude);		
			System.out.println("The closest petrol station to "+currentLocation+" is: \n "+ calculateClosestPetrolStation(list, currentLocation));
		}
		sc.close();
	}

}
