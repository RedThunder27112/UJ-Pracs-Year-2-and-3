import java.util.ArrayList;

import UI.GraphicsPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Circle;
import model.Rectangle;
import model.Shape;

public class Main extends Application
{

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GraphicsPane root = new GraphicsPane();
		
		ArrayList<Shape> shapes = new ArrayList<>();
		
		shapes.add(new Circle(20));
		shapes.add(new Rectangle(40,150));
		shapes.add(new Circle(20));
		shapes.add(new Rectangle(40,150));
		
		root.drawShapes(shapes);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Class example");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

