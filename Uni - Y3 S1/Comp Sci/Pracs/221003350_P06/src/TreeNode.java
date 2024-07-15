//************ Total marks for TreeNode: 10 marks ************************************
import java.util.Iterator;
import java.util.ArrayList;
/**
 * A Node in the tree
 *
 * @param <T> the object type that will be stored in this node
 */
public class TreeNode<T> implements Position<T> {
	T element = null;
	// !!the list of children is stored as a list of TreeNodes, however
	// a tree node extends from Position so we refer to it as a list
	// of positions!!
	private ArrayList<Position<T>> children = null;
	private TreeNode<T> parent = null;
	
	/**
	 * The constructor
	 * 
	 * @param parent - The parent of this node (null if the root)
	 * @param element - The element that will be stored in the tree
	 * 3 marks *******************************************************************
	 */
	public TreeNode(TreeNode<T> parent, T element) 
	{
		this.parent = parent;
		this.element = element;
		//TODO: Complete
	}
	
	/**
	 * Return an iterator over all of the children of this node. Only the
	 * direct children need to be iterated over
	 * @return an Iterator<Position<T>> over all of the children.
	 * 2 marks *******************************************************************
	 */
	public Iterator<Position<T>> getChildren() 
	{
		//as the variable children = null, you cannot access it beside overwriting its null value;
		if(children == null)
		{
			children = new ArrayList<Position<T>>();
		}
		return children.iterator();
		//TODO: Complete
	}
	
	/**
	 * Add a child to this node, we add a new node to the end of the ArrayList for children
	 * that are stored in this tree
	 * @param newChild The new child element
	 * @return A TreeNode<T> object that that stores the element that has been added.
	 * 5 marks *******************************************************************
	 */
	public TreeNode<T> addChild(T newChild) 
	{
		TreeNode<T> child = new TreeNode<T>(this, newChild);
		
		if(children == null)
		{
			children = new ArrayList<Position<T>>();
		}
		
		children.add(child);
		
		return child;
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
	
	public TreeNode<T> getParent(){
		return parent;
	}
	/**
	 * Return the String representation of the Node.
	 */
	public String toString() {
		return element.toString();		
	}
}
