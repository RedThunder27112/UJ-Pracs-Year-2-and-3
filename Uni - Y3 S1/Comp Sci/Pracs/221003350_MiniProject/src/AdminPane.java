import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.TYPE;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPane extends StackPane
{
	private Stack<TextArea> stackAdvantages = new Stack<TextArea>();
	private Stack<TextArea> stackDisadvantages = new Stack<TextArea>();
	
	public AdminPane(Vertex<Choice> elem, Button button,GraphPane graphPane,buildGraph buildGraph)
	{
	
		VBox vBox = new VBox();
		vBox.setPrefSize(600, 600);
		
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(600, 600);
		//setting gridPane

		//setting labels
		Label lblName = new Label("Name:");
		Label lblDescription = new Label("Description:");
		Label lblAdvantages = new Label("Advantages:");
		Label lblDisadvantages = new Label("Disadvantages");
		Label lblNumMonths = new Label("Number of Months:");
		Label lblMonthlyRandCost = new Label("Monthly Rand Cost: (R)");
		Label lblTotalRandCost = new Label("Total Rand Cost: (R)");
		Label lblMonthlyTimeCost = new Label("Monthly Time Cost: (hrs)");
		Label lblTotalTimeCost = new Label("Total Time Cost: (hrs)");
		
		//setting text boxes
		TextArea txtName = new TextArea(elem.getValue().getName());
		txtName.setPrefSize(getPrefWidth(), 30);
		
		TextArea txtDescription = new TextArea(elem.getValue().getDescription());
		txtDescription.setPrefSize(getPrefWidth(), 30);
		
		vBox.getChildren().add(lblName);
		vBox.getChildren().add(txtName);
		vBox.getChildren().add(lblDescription);
		vBox.getChildren().add(txtDescription);
		vBox.getChildren().add(lblAdvantages);

		ArrayList<String> advantages = elem.getValue().getAdvantages();
		for(String s : advantages)
		{
			TextArea txtAdvantage = new TextArea(s);
			txtAdvantage.setPrefSize(getPrefWidth(), 30);
			vBox.getChildren().add(txtAdvantage);
			stackAdvantages.push(txtAdvantage);
		}
		TextArea txtAdvantage = new TextArea();
		txtAdvantage.setPrefSize(getPrefWidth(), 30);
		vBox.getChildren().add(txtAdvantage);
		stackAdvantages.push(txtAdvantage);
		
		vBox.getChildren().add(lblDisadvantages);
		
		ArrayList<String> disadvantages = elem.getValue().getDisadvantages();
		for(String s : disadvantages)
		{
			TextArea txtDisadvantage = new TextArea(s);
			txtDisadvantage.setPrefSize(getPrefWidth(), 30);
			vBox.getChildren().add(txtDisadvantage);
			stackDisadvantages.push(txtDisadvantage);
		}
		TextArea txtDisadvantage = new TextArea();
		txtDisadvantage.setPrefSize(getPrefWidth(), 30);
		vBox.getChildren().add(txtDisadvantage);
		stackDisadvantages.push(txtDisadvantage);
		
		TextArea txtNumMonths = new TextArea(""+elem.getValue().getNumMonths());
		txtNumMonths.setPrefSize(getPrefWidth(), 30);
		
		TextArea txtMonthlyRandCost = new TextArea(""+elem.getValue().getMonthlyRandCost());
		txtMonthlyRandCost.setPrefSize(getPrefWidth(), 30);
		
		TextArea txtTotalRandCost = new TextArea(""+elem.getValue().getTotalRand());
		txtTotalRandCost.setPrefSize(getPrefWidth(), 30);
		
		TextArea txtMonthlyTimeCost = new TextArea(""+elem.getValue().getMonthlyTimeCost());
		txtMonthlyTimeCost.setPrefSize(getPrefWidth(), 30);
		
		TextArea txtTotalTimeCost = new TextArea(""+elem.getValue().getTotalTime());
		txtTotalTimeCost.setPrefSize(getPrefWidth(), 30);
		
		vBox.getChildren().add(lblNumMonths);
		vBox.getChildren().add(txtNumMonths);
		vBox.getChildren().add(lblMonthlyRandCost);
		vBox.getChildren().add(txtMonthlyRandCost);
		vBox.getChildren().add(lblTotalRandCost);
		vBox.getChildren().add(txtTotalRandCost);
		vBox.getChildren().add(lblMonthlyTimeCost);
		vBox.getChildren().add(txtMonthlyTimeCost);
		vBox.getChildren().add(lblTotalTimeCost);
		vBox.getChildren().add(txtTotalTimeCost);
	
		

		
		//setting buttons
		Button btnUpdate = new Button("Update Changes");
		
		Button btnDelete = new Button("Delete Node");
		Button btnBack = new Button("Back");
		
		btnUpdate.setPrefSize(600, 30);
		btnDelete.setPrefSize(600, 30);
		btnBack.setPrefSize(600, 30);
		
	
		//add nodes to pane
		vBox.getChildren().add(btnUpdate);
		
		vBox.getChildren().add(btnDelete);
		vBox.getChildren().add(btnBack);
		
		scrollPane.setContent(vBox);
		
		this.getChildren().add(scrollPane);
		
		btnUpdate.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				elem.getValue().setName(txtName.getText());
				elem.getValue().setDescription(txtDescription.getText());
				
				//adding new advantage
				String tempAdvantage = stackAdvantages.pop().getText();
				if(!tempAdvantage.equals(""))
				{
					elem.getValue().addAdvantage(tempAdvantage);
				}
				
				while(!stackAdvantages.isEmpty())
				{
					tempAdvantage = stackAdvantages.pop().getText();
					int i = stackAdvantages.size();
					
					if(tempAdvantage.equals(""))
					{
						elem.getValue().removeAdvantage(i);
					}else
					{
						elem.getValue().setAdvantages(tempAdvantage, i);
					}
				}
				
				//adding new advantage
				String tempDisadvantage = stackDisadvantages.pop().getText();
				if(!tempDisadvantage.equals(""))
				{
					elem.getValue().addDisadvantages(tempDisadvantage);
				}
				
				while(!stackDisadvantages.isEmpty())
				{
					tempDisadvantage = stackDisadvantages.pop().getText();
					int i = stackDisadvantages.size();
					
					if(tempDisadvantage.equals(""))
					{
						elem.getValue().removeDisadvantage(i);
					}else
					{
						elem.getValue().setDisadvantages(tempDisadvantage, i);
					}
					
					
				}
				
				try
				{
					elem.getValue().setNumMonths(Integer.parseInt(txtNumMonths.getText()));
					elem.getValue().setMonthlyRandCost(Double.parseDouble(txtMonthlyRandCost.getText()));
					elem.getValue().setTotalRand();
					elem.getValue().setMonthlyTimeCost(Integer.parseInt(txtMonthlyTimeCost.getText()));
					elem.getValue().setTotalTime();
					elem.setWeight((int)elem.getValue().getTotalRand());
				}catch(Exception e)
				{
					elem.getValue().setNumMonths(1);
					elem.getValue().setMonthlyRandCost(1);
					elem.getValue().setTotalRand();
					elem.getValue().setMonthlyTimeCost(1);
					elem.getValue().setTotalTime();
				}

				//add selection to path
				//closes old window
				vBox.getScene().getWindow().hide();
				graphPane.getScene().getWindow().hide();
				graphPane.UpdateOccured();


				//opens main menu
				
				graphPane.setVisible(true);

				Stage secondStage = new Stage();
				
				//secondStage.initStyle(StageStyle.UNDECORATED);
				GraphPane root = new GraphPane();
		        secondStage.setScene(new Scene(root));
		        secondStage.show();
		        
				FilePersistence save = new FilePersistence();
				save.saveFile(buildGraph.getGraph(), buildGraph.getRoot());
				

			}			
		});
		
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
		
		
		btnDelete.setOnAction(new EventHandler <ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{	
				graphPane.UpdateOccured();
				List<?> tempEdges = graphPane.graph.getEdges();
				List<?> tempVertices = graphPane.graph.getVertices();
				
				Vertex<Choice> parent = GraphPane.getParent(elem);
				ArrayList<Vertex<Choice>> children = GraphPane.getChildren(parent);
				
				for(Vertex<Choice> v : children)
				{
					if(v.getValue().getChildNum() > elem.getValue().getChildNum())
					{
						v.getValue().setChildNum(v.getValue().getChildNum()-1);
					}
				}

				elem.getValue().setDeleted();
				
				tempVertices.remove(elem);
				tempVertices.remove(parent.getEdge(elem).getToVertex());
				tempEdges.remove(parent.getEdge(elem));

				if(graphPane.getChildren(elem).size() > 0)
				{
					tempEdges.remove(elem.getEdge(graphPane.getChildren(elem).get(0)));
				}
				
	
				graphPane.graph = new Graph(TYPE.DIRECTED,tempVertices,tempEdges);
				//add selection to path
				//closes old window
				
				vBox.getScene().getWindow().hide();
				graphPane.getScene().getWindow().hide();
				
				//opens main menu
				
				graphPane.setVisible(true);

				Stage secondStage = new Stage();
				
				//secondStage.initStyle(StageStyle.UNDECORATED);
				GraphPane root = new GraphPane();
		        secondStage.setScene(new Scene(root));
		        secondStage.show();
		        
				FilePersistence save = new FilePersistence();
				save.saveFile(buildGraph.getGraph(), buildGraph.getRoot());
			}			
		});
		
		
	}

}
