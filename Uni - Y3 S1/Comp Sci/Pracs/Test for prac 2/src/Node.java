
public class Node 
{
	Object node;
	Node nextNode;
	
	public Node(Object node, Node nextNode)
	{
		this.node = node;
		this.nextNode = nextNode;
		
	}
	
	public Object getElement()
	{
		return node;
	}
	
	public Node getNextNode()
	{
		return nextNode;
	}
	
	public void setNode(Object node)
	{
		this.node = node;
	}
	
	public void setNextNode(Node nextNode)
	{
		this.nextNode = nextNode;
	}
	
	//get student at X position
	public Object getNodePosition(int pos, Node tnode)
	{
		Object tempNextNode = null;
		Object tempNode = getElement();
		for(int i = 0; i < pos-1; i++)
		{
			tempNextNode = tnode.getNextNode();
			tnode = (Node) tempNextNode;
			tempNode = tnode.getElement();
		}
		
		return tempNode;
	}
	
	public Object getNodeID(int ID, Node tnode)
	{
		Object tempNextNode = null;
		Object tempNode = getElement();
		boolean flag = false;
		while(!flag)
		{
			tempNextNode = tnode.getNextNode();
			tnode = (Node) tempNextNode;
			tempNode = tnode.getElement();
			
			if(tempNode instanceof Student)
			{
				int id = ((Student) tempNode).getID();

				if(id == ID)
				{
					flag = true;
				}
			}
		}
		
		
		return tempNode;
	}
	
	//insert
	public void insert(int pos, Node tnode, Object element)
	{
		Object tempNextNode = null;
		Node tempPrevNode = null;
		for(int i = 0; i < pos-1; i++)
		{
			tempNextNode = tnode.getNextNode();
			tempPrevNode = tnode;
			tnode = (Node) tempNextNode;
			
		}
		
		Node newNode = new Node(element,tnode);
		tempPrevNode.setNode(newNode);
		
	}
	
	public void display()
	{
		Object tempNextNode = null;
		Object tempNode = getElement();
		boolean flag = false;
		while(!flag)
		{
			tempNextNode = tnode.getNextNode();
			tnode = (Node) tempNextNode;
			tempNode = tnode.getElement();
			
			if(tempNode instanceof Student)
			{
				if((((Student) tempNode).getID()))
				{
					
				}
				int id = ((Student) tempNode).getID();

				if(id == ID)
				{
					flag = true;
				}
			}
		}
		
		
		return tempNode;
		
	}
	
	//delete
	
	//return
	
	//given position and/or element
	
	

}
