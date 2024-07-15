import java.io.Serializable;

//Overall PriorityQueue class: 15 marks ***********************************************

public class PriorityQueue<K extends Comparable <? super K>,V> {
	private PositionList<Entry<K,V>> list = null;
	
	/*
	 * Default constructor
	 */
	public PriorityQueue(){
		this.list = new PositionList<Entry<K,V>>();
	}
	
	/*
	 * Insert method that store the entry in an unordered manner (you can add it at the end)	 
	 */
	public void insert(Entry<K,V> entry){
		list.addLast(entry);		
	}
	
	/*
	 * Remove the entry with the highest priority (typically close to 0 as an integer)
	 * by searching for the lowest key
	 * 8 marks ***********************************************
	 */
	public Entry<K,V> removeMin()
	{
		Entry<K,V> removeEntry = min();
		
		Entry<K,V> retEntry = new Entry<K,V>(removeEntry.getKey(),removeEntry.getValue());
		boolean flag = false;
		Entry<K, V> prevEntry = null;;

		 PositionListIterator<Entry<K,V>> iterator =  entries();
		 
		 while(iterator.hasNext())
		 {
			 
			Entry<K,V> entry = iterator.next();

			//if entry was removed, now move every element to left
			if(flag)
			{
				prevEntry.setKey(entry.getKey());
				prevEntry.setValue(entry.getValue());
			}
			//set remove element flag as true
			if(entry.equals(removeEntry))
			{
				flag = true;		
			}
			//set current entry, as the next previous entry
			prevEntry = entry;
			
			//if at end of list, set last value ass null
			if(!iterator.hasNext())
			{

				prevEntry.setKey(null);
				prevEntry.setValue(null);
				prevEntry = null;
			}
		 }
		 
		return retEntry;		
	}
	
	/*
	 * Returns the entry with the highest priority (typically close to 0 as an integer) 
	 * by searching for the lowest key
	 * 5 marks
	 */
	public Entry<K,V> min()
	{
		
		
		 PositionListIterator<Entry<K,V>> iterator =  entries();
		 Entry<K,V> minEntry = null;
		 while(iterator.hasNext())
		 {
			Entry<K,V> entry = iterator.next();
			
			if(minEntry == null)
			{	
				minEntry = entry;
			}
			
			if(entry != null)
			{
				if(entry.getKey() != null)
				{
					if(minEntry.compareTo(entry) == 1)
					{
						minEntry = entry;
					}
				}
			}
		 }
		

		//TODO: Implement the unsorted list min function
		return minEntry;
	}	
	
	
	/*
	 * Returns the size of the Priority Queue
	 */
	public Integer size(){
		return this.list.size();
	}
	
	/*
	 * Returns whether the Priority Queue is empty or not.
	 */
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	/*
	 * The overridden toString method
	 */
	public String toString(){
		return list.toString();
	}
	
	/*
	 * Returns the iterator containing all the Entries in the PriorityQueue
	 * 2 marks  ***********************************************
	 */
	
	public PositionListIterator<Entry<K,V>> entries()
	{

		return (PositionListIterator<Entry<K, V>>) list.iterator();
	}
	
	/**
	 * Convert a Position<T> into a Entry<K,V>
	 * @param p a Position<T> to convert
	 * @return the corresponding Entry<K,V>
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V> checkPosition(Position<Entry<K,V>> p) {
		if (!(p instanceof Entry<?,?>)) {
			throw new PositionException("Invalid Position");
		}
		
		return (Entry<K,V>)p;
	}
}
