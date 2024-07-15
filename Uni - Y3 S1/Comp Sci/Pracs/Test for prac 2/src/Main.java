
public class Main {

	public static void main(String[] args) 
	{
		Student s1 = new Student(1);
		Student s2 = new Student(2);
		Student s3 = new Student(3);
		Student s4 = new Student(4);
		
		Node n4 = new Node(s4,null);
		Node n3 = new Node(s3,n4);
		Node n2 = new Node(s2,n3);
		Node n1 = new Node(s1,n2);
		
		//return by position
		Object obj = n1.getNodePosition(4,n1);
		
		if(obj instanceof Student)
		{
			int ID = ((Student) obj).getID();
			System.out.println(ID);
		}
		
		//return by ID
		Object obj2 = n1.getNodeID(3,n1);
		
		if(obj instanceof Student)
		{
			int name = ((Student) obj2).getName();
			System.out.println(name);
		}
		

	}

}
