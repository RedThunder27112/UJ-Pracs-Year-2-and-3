package model;

public class Circle extends Shape
{
	private double radius;
	
	public Circle(double radius)
	{
		this.radius = radius;
	}

	@Override
	public void calculateArea() {
		area = Math.PI*Math.pow(radius, 2);

	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void accept(IPaintVisitor visitor) 
	{
		visitor.visit(this);
		
	}
	
	

}
