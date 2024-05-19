package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintGraphics implements IPaintVisitor
{
	private GraphicsContext gc;
	
	public void setGraphicsContext(GraphicsContext gc)
	{
		this.gc = gc;
	}
	
	@Override
	public void visit(Circle circle) 
	{
		gc.setFill(Color.BLUE);
		gc.fillOval(new Random().nextInt(400),new Random().nextInt(400),circle.getRadius(),circle.getRadius());
		
	}

	@Override
	public void visit(Rectangle rectangle) 
	{
		gc.setFill(Color.RED);
		gc.fillRect(new Random().nextInt(400), new Random().nextInt(400), rectangle.getLength(), rectangle.getWidth());
		
	}

}
