
/**
 * A Node in the tree
 *
 * @param <T> String the object type that will be stored in this node
 */
public class BTNode<T> implements BTPosition<T> {
	protected T element = null;
	protected BTNode<T> left  = null;
	protected BTNode<T> right = null;
	protected BTNode<T> parent = null;
	
	/**
	 * The constructor
	 * 
	 * @param parent - The parent of this node (null if the root)
	 * @param element the element that will be stored in the tree
	 */
	public BTNode(BTNode<T> parent, BTNode<T> left, BTNode<T> right, T element) {
		this.parent = parent;
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Set the element in this node
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	@Override
	/**
	 * Get element from the TreeNode
	 */
	public T element() {
		return element;
	}

	/**
	 * Return the String representation of the Node.
	 */
	public String toString() {
		if (element == null) {
			return "()";
		}
		
		String ret = "(" + element.toString() + ")";
		return ret;
	}

	public BTNode<T> left() {
		return left;
	}

	public BTNode<T> right() {
		return right;
	}

	public BTNode<T> parent() {
		return parent;
	}
	
	public void setParent(BTPosition<T> parent) {
		this.parent = (BTNode<T>)parent;
	}
	
	public void setLeft(BTPosition<T> left) {
		this.left = (BTNode<T>)left;
	}
	
	public void setRight(BTPosition<T> right) {
		this.right = (BTNode<T>)right;
	}
}
