
public class Node<T> 
{
	/**
	 * next - is the next node that the current node points to
	 * prev - is the previous node that the current node points to
	 * element - is the stored value of the node
	 */
	Node<T> next;
	Node<T> prev;
	T element;
	//constructor
	/**
	 * Node constructor - creates a node
	 * @param next is the next node that the current node points to
	 * @param prev is the previous node that the current node points to
	 * @param element is the stored value of the node
	 */
	
	public Node(Node<T> next, Node<T> prev,T element )
	{
		setNext(next);
		setPrev(prev);
		setElement(element);
		
	}
	
	/**
	 * 
	 * @param next - sets the next node that the current node points to
	 */
	//mutators
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	
	/**
	 * 
	 * @param prev - sets the previous node that the current node points to
	 */
	public void setPrev(Node<T> prev)
	{
		this.prev = prev;
	}
	
	/**
	 * 
	 * @param element - sets the element that the current node points to
	 */
	public void setElement(T element)
	{
		this.element = element;
	}
	//accessors
	
	/**
	 * 
	 * @return next - the next node that the current node points to
	 */
	public Node<T> getNext()
	{
		return next;
	}
	/**
	 * 
	 * @return prev - the previous node that the current node points to
	 */
	
	public Node<T> getPrev()
	{
		return prev;
	}
	/**
	 * 
	 * @return element - the value of the node that the current node points to
	 */
	public T getElement()
	{
		return element;
	}
}
