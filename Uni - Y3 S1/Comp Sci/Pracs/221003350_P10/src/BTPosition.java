
/**
 * The Position interface
 *
 * @param <T> the type of the object for this position
 */
public interface BTPosition<T> {
	public T element();
	public BTPosition<T> left();
	public BTPosition<T> right();
	public BTPosition<T> parent();
	
	public void setElement(T setElement);
	public void setParent(BTPosition<T> parent);
	public void setLeft(BTPosition<T> left);
	public void setRight(BTPosition<T> right);
}
