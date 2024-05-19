package model;

public abstract class Shape implements IPaintable/////
{
	protected double area;
	
	public Shape()
	{
		area = 0;
		
	}
	
	public abstract void calculateArea();
	
	public double getArea()
	{
		return area;
	}

}
