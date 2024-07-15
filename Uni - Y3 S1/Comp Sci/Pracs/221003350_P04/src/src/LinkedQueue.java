/**
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList.
 * Total Marks LinkedStack Class: 15 Marks
 */
public class LinkedQueue<T> implements IQueue<T>, Cloneable {

	/** The underlying singly linked list */
	private SList<T> list;
	
	private int size = 0;

	/**
	* Default constructor
	*/
	public LinkedQueue() {
		list = new SList<>();
	}
	/**
	* Returns the number of elements in the queue.
	* @return number of elements in the queue
	* 2 Marks
	*/
	@Override
	public int size() 
	{
		return list.size();
	}

	/**
	* Tests whether the queue is empty.
	* @return true if the queue is empty, false otherwise
	* 2 Marks
	*/
	@Override
	public boolean isEmpty() 
	{
		//checks if size is 0/empty
		if(size == 0)
		{
			return true;
		}
		
		return false;
	}

	/**
	* Inserts an element at the rear of the queue.
	* @param element  the element to be inserted
	* 2 Marks
	*/
	@Override
	public void enqueue(T elem) 
	{
		size++;
		//add element to rear
		list.addLast(elem);
	}
	
	/**
	* Returns, but does not remove, the first element of the queue.
	* @return the first element of the queue (or null if empty)
	* 2 Marks
	*/
	@Override
	public T first() 
	{
		//checks if empty
		if(isEmpty())
		{
			return null;
		}
		//if not, return first element
		return list.first();
	}

	/**
	* Removes and returns the first element of the queue.
	* @return element removed (or null if empty)
	* 2 Marks
	*/
	@Override
	public T dequeue() 
	{
		
		//checks if empty
		if(isEmpty())
		{
			return null;
		}
		size--;
		
		//removes first elem and returns it
		T elem = list.removeFirst();
		
		return elem;
	}

	/**
	* Returns clone of LinkedQueue
	* @return clone of LinkedQueue
	* 5 Marks
	*/
	@Override
	public LinkedQueue<T> clone() 
	{
		//creates an empty SLIST copy and fills its list
		SList<T> cloneList = new SList<T>();
		cloneList = list.clone();
		//creates a empty linkedQueue and fills it 
		LinkedQueue<T> cloneQueue = new LinkedQueue<T>();
		cloneQueue.list = cloneList;
		
		return cloneQueue;
	}	
	
	/**
	* Method for displaying (front to back) and serialising items in the queue
	* @return string representation of the queue
	*/
	public String toString() {
		return list.toString();
	}
}
