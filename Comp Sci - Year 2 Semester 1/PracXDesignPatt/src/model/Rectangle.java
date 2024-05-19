package model;

public class Rectangle extends Shape
{
	private int length;
	private int width;
	
	
	public Rectangle(int length, int width) {
		super();
		this.length = length;
		this.width = width;
	}

	@Override
	public void calculateArea() {
		// TODO Auto-generated method stub

	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public void accept(IPaintVisitor visitor) 
	{
		visitor.visit(this);
		
	}
	
	

}
