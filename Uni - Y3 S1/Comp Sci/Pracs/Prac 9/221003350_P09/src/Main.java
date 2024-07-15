import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
//Correctness: 10 marks ***********************************************

public class Main {
	static int MAX = 12;
	static int N = 13;
	public static void main(String[] args) {
		int count = 0;
		HashTable<Integer,String> ht = new HashTable<Integer, String>(N);
		Random r = new Random();		
		ArrayList<Integer> al = new ArrayList<>();
		
		for(int i=0; i<MAX;i++) {
			int num = r.nextInt(100);
			al.add(num);
		}
		System.out.println("***********************************************");
		System.out.println("			Key insertion order");
		System.out.println(al);
		System.out.println("***********************************************");
		System.out.println("			Key insertion");
		while(count < MAX) {	
			ht.put(al.get(count),"Number "+count);
			System.out.println(ht);
			count++;
		}
		
		System.out.println("***********************************************");
		System.out.println("			Key removal");
		Iterator<Integer> keys = ht.keys();
		while(keys.hasNext()) {
			Integer currentKey = keys.next();
			System.out.println("Removing "+ht.get(currentKey));
			ht.remove(currentKey);
		}

	}

}
