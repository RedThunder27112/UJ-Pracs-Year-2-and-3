import java.io.File;
import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ResultsPane extends StackPane
{
	private final File file = new File("docs","background.jpg");//gets image
	private final Image image = new Image(file.getAbsolutePath().toString());
	
	public ResultsPane(ArrayList<Vertex<Choice>> vertexPath, GraphPane graphPane, int totalMoney, int totalTime)
	{
		BorderPane borderPane = new BorderPane();
	    this.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
		
		VBox vBox = new VBox();
		vBox.setPrefSize(800, 600);
		
		//setting buttons
		Button btnBack = new Button("Back");
		btnBack.setPrefSize(800, 30);
		
		ListView listView = new ListView();
		listView.setPrefSize(USE_COMPUTED_SIZE, 500);
		//adds choice to list

		boolean disp = false;
		for(Vertex<Choice> m : vertexPath)
		{
			if(disp)
			{
				listView.getItems().addAll(addChoice(m.getValue().getName(), m.getValue().toString()));
				disp = false;
			}else
			{
				disp = true;
			}
			
			
		}
		
		String totals = "";
		totals += "Total Money: R" + totalMoney + "\n";
		totals += "Money needed per year: R" + (double)totalMoney/(12) + "\n";
		totals += "Money needed per month: R" + (double)totalMoney/(12*12) + "\n";
		totals += "Money needed per day: R" + (double)totalMoney/(30*12*12) + "\n\n";
		totals += "Total Time: " + totalTime + "hrs\n";
		totals += "Time needed per year: " + (double)totalTime/(12) + "hrs\n";
		totals += "Time needed per month: " + (double)totalTime/(12*12) + "hrs\n";
		totals += "Time needed per day: " + (double)totalTime/(30*12*12) + "hrs\n\n";
		listView.getItems().addAll(addChoice("Totals:", totals));
		
		//add nodes to pane
		vBox.getChildren().add(listView);
		vBox.getChildren().add(borderPane);
		vBox.getChildren().add(btnBack);
		this.getChildren().add(vBox);
		

		
		btnBack.setOnAction(new EventHandler <ActionEvent>()
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
	
	private static TitledPane addChoice(String choiceName, String choiceString)
	{
		//text Field
		System.out.println(choiceString);
		TextArea txtChoice = new TextArea();
		txtChoice.setText(choiceString);
		txtChoice.setPrefSize(800, 1000);
		
		//Pane
		Pane pane = new Pane();
		pane.getChildren().add(txtChoice);
		
		//creating scrollpane
		ScrollPane scrollPane = new ScrollPane();
		
		scrollPane.setContent(pane);
		scrollPane.setPrefSize(USE_COMPUTED_SIZE, 250);///size of scrollpane
		//scrollPane.add(txtMail,0,0);
		

		//TitlePane
		TitledPane titlePane = new TitledPane(choiceName,scrollPane);
		titlePane.setExpanded(false);
		
		return titlePane;
	}
	
	

}
