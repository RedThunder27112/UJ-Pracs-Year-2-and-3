
/**
 * An entry for key value pairs 
 *
 * @param <K> the object type for the key
 * @param <V> the object type for the value
 */
public class Entry<K,V> {
	private K key;
	private V value;
	/**
	 * Constructor - Takes an existing key and value to construct an entry 
	 * @param key The key to construct the entry with
	 * @param value The value to construct the entry with
	 */
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * The accessor method for the key
	 * @return returns the entry's key
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * The accessor method for the value
	 * @return returns the entry's value
	 */
	public V getValue() {
		return this.value;
	}
	
	/**
	 * The mutator method for the key
	 * @param key The new key to update with
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	/**
	 * The mutator method for the value
	 * @param value The new value to update with
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * The string based representation of the entry containing the key and value
	 */
	public String toString() {
		return key.toString() + "," + value.toString();
	}
	
}
