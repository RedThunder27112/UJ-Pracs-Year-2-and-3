package UI;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import model.PaintGraphics;
import model.Shape;

public class GraphicsCanvas extends Canvas //this is the "client" class on diagram
{
	private ArrayList<Shape> shapes;
	private PaintGraphics visitor; //visitor
	public final int CANVAS_WIDTH = 500;
	public final int CANVAS_HEIGHT = 500;
	
	public GraphicsCanvas()
	{
		shapes = new ArrayList<Shape>();
		visitor = new PaintGraphics();
		setWidth(500);
		setHeight(500);
	}
	

	public void drawShapes(ArrayList<Shape> shape) 
	{
		this.shapes = shape;
		redrawCanvas();
	}
	
	public void redrawCanvas()
	{
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, CANVAS_HEIGHT, CANVAS_WIDTH);
		
		visitor.setGraphicsContext(gc);
		
		for(Shape shape : shapes)
		{
			shape.accept(visitor);
		}
	}

	
	
}
