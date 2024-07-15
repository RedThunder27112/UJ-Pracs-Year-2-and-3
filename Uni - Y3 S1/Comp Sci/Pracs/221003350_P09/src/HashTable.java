import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.ArrayList;
//Overall Hash Table: 40 marks ***********************************************

public class HashTable<K,V> implements IMap<K,V> {
	Object[] table;
	int size;
	int capacity;

	/**
	 * Default constructor
	 */
	public HashTable() {
		this(20);
	}
	
	/**
	 * Constructor - provides the size of the array
	 * @param initialSize the initial size
	 */
	public HashTable(int initialSize) {
		this.capacity = initialSize;
		this.table = createArray(this.capacity);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Create an array
	 * @param size the size of the array to create
	 * @return the array that was created
	 * 
	 */
	private Object[] createArray(int size) {
		Object[] arr = new Object[size];					
		return arr;
	}
	
	/**
	 * Hash a string input
	 * @param str The input string
	 * @return the hash code for the integer
	 */
	private long hash(String str) {
		return hash(str.getBytes());
	}
	
	/**
	 * A hash an integer input
	 * @param inputInt the input input
	 * @return the hash code for the integer
	 */
	private long hash(int inputInt) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(inputInt).array();
		return hash(bytes);
	}
	
	/**
	 * Calculate a hash code using the djb2 hash function
	 * This hash function was created by Dan Bernstein, however
	 * normally it works with string inputs, this has been modified
	 * to work with byte inputs
	 * @param input the input array of bytes
	 * @return a hash value for the input
	 */
	private long hash(byte[] input) {
		long hash = 5381;
		for (int i = 0; i < input.length; i++) {
			hash = ((hash << 5) + hash) + input[i];
		}
		return hash;
	}
	
	/**
	 * Calculate a hash for either a string or an Integer
	 * @param item the item to hash
	 * @return a compressed hash code for the item
	 */
	private long hash(K item) {
		if (item instanceof Integer) {
			return hash((Integer)item) % capacity;
		}
		
		if (item instanceof String) {
			return hash((String)item) % capacity;
		}
		
		return (long)item.hashCode() % capacity;
	}
	
	@Override
	/**
	 * Remove an item from the hash table
	 * @param key the key of the item to remove
	 * 8 Marks
	 * 
	 */
	public V remove(K key) 
	{
		if(isEmpty())
		{
			return null;
		}

		long hash =  hash(key);
		int h = (int) (hash % capacity);
	
		
		for(int i = 0; i < capacity; i++)
		{
			Entry<K,V> tempEntry = (Entry<K, V>) table[h];
			
			//no collision
			if(tempEntry == null)
			{
				//System.out.println("n");
				h = (h+hashBonus(key)) % capacity;
			}else
			if(tempEntry.getKey() == key)
			{
				size--;
				V tempV = tempEntry.getValue();
				tempEntry.setKey(null);
				tempEntry.setValue(null);
				return tempV;
			}else
			{
				
				//collision
				h = (h+hashBonus(key)) % capacity;
				
			}	

		}
		
		
		
		
		
		//TODO: the remove function for the Hashtable with a linear probing handling strategy
		
		return null;	
	}

	@Override
	/**
	 * Get the value for a given key
	 * @param key the key for the item
	 * @returns the value for the associated key
	 * 8 Marks
	 */
	public V get(K key) 
	{
		long hash =  hash(key);
		
		int h = (int) (hash % capacity);
		
		for(int i = 0; i < capacity; i++)
		{
			Entry<K,V> tempEntry = (Entry<K, V>) table[h];
			
			//no collision
			if(tempEntry == null)
			{
				//System.out.println("n");
				h = (h+hashBonus(key)) % capacity;
			}else
			if(tempEntry.getKey() == key)
			{
				//System.out.println("y");
				//System.out.println(h +"   " + tempEntry.getKey() + "    " + key.toString());
				return tempEntry.getValue();
			}else
			{
				
				//collision
				h = (h+hashBonus(key)) % capacity;
				
			}	

		}
	
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Put an item into the hash table
	 * @param key the key for the item (unique)
	 * @param value the value for the item
	 * 8 Marks
	 */
	public void put(K key, V value) 
	{
		if(size >= capacity)
		{
			return;
		}
		
		long hash =  hash(key);
		int h = (int) (hash % capacity);

		boolean flag = false;
		while(!flag)
		{
			//no collision
			if(table[h] == null)
			{
				flag = true;
				size++;
				table[h] = new Entry<K,V>(key,value);
			}else
			{
				//collision
				h = (h+hashBonus(key)) % capacity;
				
			}
		}
		//TODO: the put function for the Hashtable with a linear probing handling strategy
		
		return;
	}
	
	/**
	 * bonus double hash function
	 * @param key
	 * @return
	 */
	private int hashBonus(K key)
	{
		long hash =  hash(key);
		int h = (int) (hash);
		//bonus
		int doubleHash = 0;
		
		if(h != 7)
		{
			doubleHash = (7-h) %7;
		}else
		{
			doubleHash = (3-h) %3;
		}
		
		if(doubleHash < 0)
		{
			doubleHash *= -1;
		}
		if(doubleHash == 0)
		{
			doubleHash++;
		}
		
		return doubleHash;
		//bonus
	}

	@Override
	/**
	 * Returns an iterator over the keys of the hash table
	 * 8 Marks 
	 */
	public Iterator<K> keys() 
	{
		ArrayList<K> arrayList = new ArrayList<K>();
		
		for(int i = 0; i < capacity; i++)
		{
			Entry<K,V> e = (Entry<K, V>) table[i];
			
			if(e != null)
			{
				arrayList.add(e.getKey());
			}
		}
		return arrayList.iterator();
	}

	@Override
	/**
	 * Returns an iterator over the values in the hash table
	 * 8 Marks
	 */
	public Iterator<V> values() 
	{
		ArrayList<V> arrayList = new ArrayList<V>();
		
		for(int i = 0; i < capacity; i++)
		{
			Entry<K,V> e = (Entry<K, V>) table[i];
			
			if(e != null)
			{
				arrayList.add(e.getValue());
			}
		}
		return arrayList.iterator();

	}

	@Override
	/**
	 * Returns the size of the hashtable
	 */
	public int size() {
		return this.size;
	}

	@Override
	/**
	 * Returns true if the hashtable is empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Returns a String representation of the keys in the HashTable
	 */
	public String toString() {
		String out= "[";
		for(int i=0;i<capacity;i++) {
			if(table[i]==null)
				out+="\t,";
			else {
				@SuppressWarnings("unchecked")
				Entry<K,V> e= (Entry<K,V>)table[i];
				out+=e.getKey()+",";
			}
		}			
				out+="]\n";
				out+="[";
			for(int i=0;i<capacity;i++) {
				out+="\t"+i;
			}
			out+="]\n";
				
		return out;
	}

}
