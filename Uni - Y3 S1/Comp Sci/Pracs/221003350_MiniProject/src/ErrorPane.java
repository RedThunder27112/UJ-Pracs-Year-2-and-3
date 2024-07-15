import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ErrorPane extends GridPane
{
	public ErrorPane(String errorMessage, GraphPane graphPane)
	{
		//setting gridPane
		GridPane gridPane = new GridPane();
		//gridPane.setPrefSize(500, 120);
		
		//setting labels
		Label lblError = new Label(errorMessage);
		
		//setting buttons
		Button btnOK = new Button("Understood");
		btnOK.setPrefSize(100, 30);

		
	
		//add nodes to pane
		gridPane.add(lblError, 0, 0);//col row
		gridPane.add(btnOK, 0, 1);//col row

		
		this.getChildren().add(gridPane);
		
		btnOK.setOnAction(new EventHandler <ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{		
				//closes old window
				
				gridPane.getScene().getWindow().hide();
				//opens main menu
				graphPane.setVisible(true);
				//borderPane.setVisible(true);

			}			
		});
		
	}

}
