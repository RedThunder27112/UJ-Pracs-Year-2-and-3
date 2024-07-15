
public interface IList<T extends Comparable<T>> {
	public Node<T> first();
	public Node<T> last();
	
	public Node<T> addAfter(Node<T> elem, T item);
	public Node<T> addBefore(Node<T> elem, T item);
	public Node<T> addFirst(T item);
	public Node<T> addLast(T item);
	public T remove(Node<T> elem);
	public Node<T> search(T elem);
	
	public T head();
	public IList<T> tail();
	
	public boolean isEmpty();
	public Integer size();
}
