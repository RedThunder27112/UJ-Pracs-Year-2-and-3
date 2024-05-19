
import acsse.csc2a.ui.TelescopePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main class - runs/launches program see TelescopePane class
 * @author Ariel Sischy
 * @Version P05
 */

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		TelescopePane root = new TelescopePane();//creates gui
		primaryStage.setScene(new Scene(root));//sets gui on scene
		primaryStage.show();//shows gui
		
	}

	
}