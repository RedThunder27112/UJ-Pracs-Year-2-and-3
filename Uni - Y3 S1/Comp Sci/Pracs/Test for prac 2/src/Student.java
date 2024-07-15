
public class Student 
{
	int ID;
	int name;
	
	public Student(int ID)
	{
		this.ID = ID;
		name = ID + 10;
		
	}
	
	public int getID()
	{
		return ID;
	}
	
	public int getName()
	{
		return name;
	}

}
