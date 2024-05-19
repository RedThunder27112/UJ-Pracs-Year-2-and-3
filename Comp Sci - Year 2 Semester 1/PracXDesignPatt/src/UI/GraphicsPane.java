package UI;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import model.Shape;


public class GraphicsPane extends StackPane 
{
	private GraphicsCanvas canvas;
	
	public GraphicsPane()
	{
		canvas = new GraphicsCanvas();
		this.getChildren().add(canvas);
	}
	
	public void drawShapes(ArrayList<Shape> shape)
	{	
		canvas.drawShapes(shape);
	}

}
