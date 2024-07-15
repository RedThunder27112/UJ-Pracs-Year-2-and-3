import java.io.File;

import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoPane extends GridPane
{
	private final File file = new File("docs","background.jpg");//gets image
	private final Image image = new Image(file.getAbsolutePath().toString());
	
	public InfoPane(Vertex<Choice> elem, Button button,GraphPane graphPane)
	{
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600, 100);
		
		VBox vBox = new VBox();
		vBox.setPrefSize(600, 500);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(600, 500);
		//setting gridPane
		
		Pane pane = new Pane();
		pane.setPrefSize(600, 500);
		pane.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
		
		//setting labels
		Label lblOutput = new Label(elem.getValue().toString());
		lblOutput.setTextFill(Color.WHITE);

		//setting buttons
		Button btnYes = new Button("Yes");
		Button btnNo = new Button("No");
		btnYes.setPrefSize(300, 50);
		btnNo.setPrefSize(300, 50);
		
	
		//add nodes to pane
		pane.getChildren().add(lblOutput);
		scrollPane.setContent(pane);
		borderPane.setLeft(btnYes);
		borderPane.setRight(btnNo);
		
		vBox.getChildren().add(scrollPane);
		vBox.getChildren().add(borderPane);
		this.getChildren().add(vBox);
		
		btnYes.setOnAction(new EventHandler <ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{	
				//add selection to path
				graphPane.addToPath(elem, button);
				//closes old window
				vBox.getScene().getWindow().hide();
				//opens main menu
				graphPane.setVisible(true);

			}			
		});
		
		btnNo.setOnAction(new EventHandler <ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{	

				//closes old window
				vBox.getScene().getWindow().hide();
				//opens main menu
				graphPane.setVisible(true);
			}			
		});
		
		
		
	}

}
