package acsse;
/*
 * Total Marks SList Class: 26 Marks
 */
public class SList<T extends Comparable<T>> implements IList<T> {

	private Node<T> head = null;
	private Node<T> tail = null;
	private Integer size = 0;
	
	/**
	 * Default constructor
	 */
	public SList() {}
	
	/**
	 * returns the first node in the list.
	 */
	public Node<T> first() {
		return head;
	}
	
	/**
	 * returns the last node in the list.
	 */
	public Node<T> last() {
		return tail;
	}
	
	public int getSize()
	{
		return size;
	}
	
	/**
	 * returns the node before a given node in the list.
	 * 3 marks
	 */
	@Override
	public Node<T> prev(Node<T> node) 
	{
		Node<T> headNode = first();
		Node<T> prevNode = null;
		prevNode = headNode.getNext();
		
		while(!prevNode.getNext().equals(node))
		{
			prevNode = prevNode.getNext();
		}
		
		return prevNode;
	}
	
	/**
	 * returns the next node after a given node in the list.
	 */
	@Override
	public Node<T> next(Node<T> node) 
	{
		return node.getNext();
	}
	
	/**
	 * Replace the element of a given node in the list
	 * @return the old element of the given node
	 * 1 Marks
	 */
	@Override
	public T replace(Node<T> node, T item) 
	{
		//store old node before replacing its element
		T oldElement = node.getElement();
		
		node.setElement(item);
		//return old node element
		return oldElement;
	}
	
	/**
	 * Add an element after a given node in the list
	 * 3 Marks
	 */
	@Override
	public Node<T> insertAfter(Node<T> node, T item) 
	{
		size++;
		//test if current node is tail
		if(node.equals(tail) || node.getNext().equals(null))
		{
			//set as new tail
			Node<T> newNode = new Node(null, item);
			node.setNext(newNode);
			tail = newNode;
			return newNode;
		}
		
		//find out which nodes the new one must be between
		Node<T> nextNode = node.getNext();
		//create new node
		Node<T> newNode = new Node(nextNode, item);
		//change prevNode pointer
		node.setNext(newNode);

		
		return newNode;

	}

	/**
	 * Add an element before a given node in the list
	 * 5 Marks
	 */
	@Override
	public Node<T> insertBefore(Node<T> node, T item) 
	{
		size++;
		//test if current node is head
		if(node.equals(head))
		{
			//set as new tail
			Node<T> newNode = new Node(node, item);
			head = newNode;

			return newNode;
		}
		
		//find out prev Node
		Node<T> prevNode = prev(node);
		//create new node
		Node<T> newNode = new Node(node, item);
		//change prevNode pointer
		prevNode.setNext(newNode);

		
		return newNode;
	}

	/**
	 * Add an element to the start of the list
	 * @return the new node
	 */
	public Node<T> insertFirst(T item) {
		Node<T> newNode = new Node<T>(head, item);
		head = newNode;
		if (isEmpty())
			tail = head;
		size++;
		return newNode;	
	} 

	/**
	 * Add an element to the end of the list
	 * @return the new node
	 */
	public Node<T> insertLast(T item){
		Node<T> newest = new Node<T>(null, item);
		if (isEmpty())
		  head = newest;
		else
		  tail.setNext(newest);
		tail = newest;
		size++;
		return newest;
	}
	/*
	 * Alternate implementation (is actually wrong))
	public Node<T> addLast(T item){
		//TODO: Complete
		return addAfter(tail, item);
	}*/
	
	
	/**
	 * Remove a specified node from the list. The removed element is returned
	 * 5 Marks
	 */
	@Override
	public T remove(Node<T> node) 
	{		
		size--;
		//get prev and next node
		Node<T> prevNode = prev(node);
		Node<T> nextNode = next(node);
		//set prev to point to next
		prevNode.setNext(nextNode);
		
		T tempElement = node.getElement();
		//delete node
		node.setElement(null);
		node.setNext(null);
		
		return tempElement;
	}

	/**
	 * Returns the node that contains the element that is specified as a parameter
	 * 5 Marks
	 */
	@Override
	public Node<T> search(T elem) 
	{
		Node<T> headNode = first();
		Node<T> nextNode = null;
		nextNode = headNode.getNext();
		
		if(headNode.getElement().equals(elem))
		{
			return headNode;
		}
		
		do
		{
			nextNode = nextNode.getNext();
			
		}while(!nextNode.getElement().equals(elem));
		
		return nextNode;
		//TODO: Complete
	}

	/**
	 * Returns true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Return the size of the list
	 */
	@Override
	public Integer size() {
		return size;
	}
	
	/**
	 * The overridden method for displaying items in the Singly-Linked List
	 * format: <e1><-><e2><-><e3><->
	 * 4 Marks
	 */
	@Override
	public String toString() {
		String result = "";
		Node<T> currentNode = head;
		Node<T> nextNode = currentNode.getNext();
		
		result = result + " <" + currentNode.getElement() + "><->"; 

		for(int i = 0; i < size-1; i++)
		{
			
			currentNode = currentNode.getNext();
			result = result + "<" + currentNode.getElement() + "><-> "; 
			
		}
		/*
		while(!currentNode.getNext().equals(null))
		{
			currentNode = currentNode.getNext();;
			result = result + " <" + currentNode.getElement() + "><->"; 
		}
		*/
		
		return result;
	}
	
	//unimplmented methods as needed methods on rubric different
	//ie insertAfter from above is addAfter below

	@Override
	public Node<T> addAfter(Node<T> elem, T item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> addBefore(Node<T> node, T item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> addFirst(T item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> addLast(T item) {
		// TODO Auto-generated method stub
		return null;
	}


}
