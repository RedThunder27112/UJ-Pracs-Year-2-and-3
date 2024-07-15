
/**
 * A node in an AVL tree. This has been modified to expose the height as an 
 * element of the node. The class extends BTNode
 *
 * @param <T> The type that this tree stores.
 */
public class AVLNode<T> extends BTNode<T> {
	private int height;
	
	public AVLNode(AVLNode<T> parent, AVLNode<T> left, AVLNode<T> right, T element) {
		super(parent, left, right, element);
		height = 0;
        if (left != null) {
            height = Math.max(height, 1 + left.getHeight());
        }
        if (right != null) {
            height = Math.max(height, 1 + right.getHeight());
        }
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		if (element == null) {
			return "() h=0";
		}
		
		String ret = "(" + element.toString() + ") h=" + height;
		return ret;
	}
}
