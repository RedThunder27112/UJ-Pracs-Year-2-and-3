public class Entry<K extends Comparable<? super K>,V> implements Comparable<K> {
	/*
	 * Data members
	 */
	private K key;
	private V value;
	
	/*
	 * Constructor that takes a key-value pair
	 */
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/*
	 * Accessor method for the key
	 */
	public K getKey() {
		return key;
	}
	
	/*
	 * Accessor method for the value
	 */
	public V getValue() {
		return this.value;
	}
	
	/*
	 * Mutator method for the key
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	/*
	 * Mutator method for the value
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/*
	 * compareTo method for Entries
	 * 
	 */
	public int compareTo(Entry<K, V> entry) {
		return this.compareTo(entry.getKey());
	}
	
	/*
	 * compareTo method for keys
	 * 
	 */
	@Override
	public int compareTo(K o) {	
		return key.compareTo(o);
	}
	
	/*
	 * overridden toString Method
	 */
	public String toString() {
		return key.toString() + "," + value.toString();
	}

	
	
}
