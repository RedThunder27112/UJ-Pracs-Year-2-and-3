import UI.MediaPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MediaPane root = new MediaPane();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(700);
		primaryStage.setTitle("Playing vid");
		primaryStage.show();
		
	}

}
