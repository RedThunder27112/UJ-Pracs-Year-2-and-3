import java.util.ArrayList;

public class Stack<T>
{
	T element;
	ArrayList<T> arrayList;
	int size;
	
	public Stack()
	{
		arrayList = new ArrayList<T>();
		size = 0;
		
	}
	
	//push - add
	public void push(T elem)
	{
		arrayList.add(elem);
		size++;
	}
	
	
	//pop - remove
	public T pop()
	{
		if(isEmpty())
		{
			return null;
		}
		
		T elem = arrayList.get(size-1);
		arrayList.remove(size-1);
		size--;
		return elem;
	}
	
	//top - get without remove
	public T top()
	{
		if(isEmpty())
		{
			return null;
		}
		
		T elem = arrayList.get(size-1);
		return elem;
	}
	
	//size - size
	public int size()
	{
		return size;
	}
	
	//isEmpty
	public boolean isEmpty()
	{
		if(size == 0)
		{
			return true;
		}
		return false;
		
	}

}
