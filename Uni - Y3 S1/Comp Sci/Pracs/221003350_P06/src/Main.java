//************ Total marks for Main: 10 marks ************************************
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

class Mark{
	String name;
	Integer value;
	
	public Mark(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		if (name!=null)
			return name + " " + value;
		else
			return String.valueOf(value);
	}	
}

public class Main {

	public static void main(String[] args) throws Exception {
		Random r = new Random(System.currentTimeMillis());
		
		Tree<Mark> tree = new Tree<Mark>(new Mark("CSC3A", 100));
		Position<Mark> root = tree.root();
		
		Position<Mark> st = tree.addElementAsChild(root, new Mark("ST", 50));
		Position<Mark> st1 = tree.addElementAsChild(st, new Mark(null, (r.nextInt(100))));
		Position<Mark> st2 = tree.addElementAsChild(st, new Mark(null, (r.nextInt(100))));
		
		Position<Mark> miniP = tree.addElementAsChild(root, new Mark("MP", 25));
		Position<Mark> mp = tree.addElementAsChild(miniP, new Mark(null, (r.nextInt(100))));
		
		Position<Mark> ct_pa = tree.addElementAsChild(root, new Mark("CT+PA", 25));
		
		Position<Mark> ct = tree.addElementAsChild(ct_pa, new Mark("CT", 50));		
		for(int i=0;i<3;i++){
			tree.addElementAsChild(ct, new Mark(null, (r.nextInt(100))));
		}
		
		Position<Mark> pa = tree.addElementAsChild(ct_pa, new Mark("PA", 50));		
		for(int i=0;i<8;i++){
			tree.addElementAsChild(pa, new Mark(null, (r.nextInt(100))));
		}
		
		System.out.println(tree.preOrderElementTraversal(tree, root));
		System.out.println("\nSemester Mark:" + calcSM(tree, tree.root()));
	}
	
	/**
	 * Calculate the semester mark using the weights and marks contained in the tree
	 * @param tree
	 * @return the semester mark
	 * 10 marks 
	 * @throws Exception *******************************************************************
	 */
	
	

	static double total = 0;
	static double tempTotal = 0;
	static int numChild = 0;
	static boolean wasChild = false;
	static Position<Mark> positionParent;
	private static Double calcSM(Tree<Mark> tree, Position<Mark> root) throws Exception 
	{
		
		Iterator<Position<Mark>> iterator = tree.children(root);

		while(iterator.hasNext())
		{
			Position<Mark> position = iterator.next();
			if(!tree.isInternal(tree, position))
			{
				tempTotal += position.element().value;
				numChild++;
				wasChild = true;
			}else
			if(wasChild)
			{

				tempTotal = tempTotal/numChild;
				
				int loop = tree.depth(tree, positionParent);
				for(int i = 0; i < loop; i++)
				{
					tempTotal = tempTotal*0.01*tree.parent(positionParent).element().value;
					positionParent = tree.parent(positionParent);
				}

				total +=tempTotal;
				numChild = 0;
				wasChild = false;
				tempTotal = 0;
			}
			
			
			
			positionParent = position;
			
			if(!iterator.hasNext() && numChild > 0)
			{
				tempTotal = tempTotal/numChild;
				
				int loop = tree.depth(tree, positionParent);
				for(int i = 0; i < loop; i++)
				{
					tempTotal = tempTotal*0.01*tree.parent(positionParent).element().value;
					positionParent = tree.parent(positionParent);
				}

				
				total +=tempTotal;
				numChild = 0;
				wasChild = false;
				tempTotal = 0;
			}
			
			calcSM(tree, position);
		}
		
	

		return total;
		//TODO: Complete
	}

}
