

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GraphPane extends StackPane
{
	private static Pane pane;
	private static int total;
	private static int totalTime;
	private static TextArea txtTotal;
	private static TextArea txtBudget;
	private static GridPane gridPane;
	private static GraphPane graphPane;
	private static Vertex<Choice> root;
	private static ScrollPane scrollPane;
	
	
	public static Graph<?> graph;
	private static ArrayList<Vertex<Choice>> vertexPath = new ArrayList<Vertex<Choice>>();
	private static Stack<Button> choicePath = new Stack<Button>();
	//static ArrayList<Button> choicePath = new ArrayList<Button>();
	private static ArrayList<Button> listButtons = new ArrayList<Button>();
	
	private static int choiceNumber = -1;
	private static boolean longPath = false;
	
	private static int weightVisible = 1;// 0 none - 1 wieghts
	private static ArrayList<Label> listWeightsC = new ArrayList<Label>();//money
	
	private static Button btnToggleWeight;
	private static Button btnToggleAdmin;
	
	private static Vertex<Choice> endVertice;
	
	private static boolean isAdmin = false;
	private static buildGraph modifygraph;
	
	private final File file = new File("docs","background.jpg");//gets image
	private final Image image = new Image(file.getAbsolutePath().toString());
	
	private static boolean readGraph = false;
	public GraphPane()
	{
		
		graphPane = this;
		gridPane = new GridPane();
		
		
		//setting vbox/controls
		VBox vbox = new VBox();
		vbox.setPrefSize(1200, 800);
		//setting pane/graph
		pane = new Pane();
		pane.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		
		//scrollpane
		scrollPane = new ScrollPane();
		scrollPane.setContent(pane);
		scrollPane.setPrefSize(600, 600);
		//adding both to main borderpane for layout

		//adding background

		
		
		//adding controls to vbox
		Label lblTotal = new Label("Current Total:");
		lblTotal.setTextFill(Color.WHITE);
		txtTotal = new TextArea();
		txtTotal.setPrefSize(getPrefWidth(), 30);
		
		
		Label lblBudget = new Label("Please Enter Your Total Budget Here:");
		lblBudget.setTextFill(Color.WHITE);
		txtBudget = new TextArea();
		txtBudget.setPrefSize(getPrefWidth(), 20);
		
		//add buttons
		
		Button btnGoBack = new Button("Go Back One Choice");
		btnGoBack.setPrefSize(200, 30);
		
		Button btnReset = new Button("Reset Choices");
		btnReset.setPrefSize(200, 30);
		
		Button btnDisplayChoices = new Button("Display Current Choices Info");
		btnDisplayChoices.setPrefSize(200, 30);
		
		Button btnShortPath = new Button("Shortest Path");
		btnShortPath.setPrefSize(200, 30);
		
		Button btnLongPath= new Button("Longest Path (Money is no issue)");
		btnLongPath.setPrefSize(200, 30);

		Button btnExpensiveAffordable = new Button("Route from set budget");
		btnExpensiveAffordable.setPrefSize(200, 30);
		
		Button btnHelp = new Button("Help");
		btnHelp.setPrefSize(200, 30);
		
		Button btnAdd = new Button("Add Node");
		btnAdd.setPrefSize(200, 30);
		
		
		btnToggleWeight = new Button("Toggle viewing weights");
		btnToggleWeight.setPrefSize(200, 30);
		btnToggleWeight.setStyle("-fx-background-color: RED");
		
		btnToggleAdmin = new Button("Toggle Admin");
		btnToggleAdmin.setPrefSize(200, 30);
		btnToggleAdmin.setStyle("-fx-background-color: RED");

	    this.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));
	    		

		gridPane.add(lblTotal, 0, 0);
		gridPane.add(txtTotal, 0, 1);
		gridPane.add(lblBudget, 0, 2);
		gridPane.add(txtBudget, 0, 3);
		gridPane.add(btnGoBack, 1, 0);
		gridPane.add(btnReset, 1, 1);
		gridPane.add(btnToggleWeight, 1, 2);
		gridPane.add(btnDisplayChoices, 1, 3);
		gridPane.add(btnHelp, 2, 0);
		gridPane.add(btnShortPath, 2, 1);
		gridPane.add(btnLongPath, 2, 2);
		gridPane.add(btnExpensiveAffordable, 2, 3);
		gridPane.add(btnToggleAdmin, 3, 0);
		gridPane.add(btnAdd, 3, 1);
		
		vbox.getChildren().add(gridPane);
		vbox.getChildren().add(scrollPane);
		
		this.getChildren().add(vbox);



		/*
		if(readGraph)
		{
			FilePersistence save = new FilePersistence();
			graph = save.readGraph();
			root = save.getRoot();
			modifygraph.setFromFile(graph, root);
			readGraph = false;
		}else*/
		if(graph == null)
		{
	
			//generate graph and fill it
			modifygraph = new buildGraph();
			graph = modifygraph.getGraph();
			root = modifygraph.getRoot();
		}else
		{
			
			//FilePersistence save = new FilePersistence();
			graph = modifygraph.getGraph();
			//root = save.getRoot();
			//modifygraph.setFromFile(graph, root);
		}
		

		try 
		{
			
			x(root);
			displayNodes(root);
			displayEdge(root);
			
			//System.out.println(tree.preOrderElementTraversal(tree, root));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		//set buttons actipns
		
		btnGoBack.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				goBack();
			}		
		});
		
		btnReset.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				resetNodes();
			}		
		});
		
		btnDisplayChoices.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{

				printChoices();
				graphPane.setVisible(false);
				//opens main menu
				ResultsPane root = new ResultsPane(vertexPath, graphPane,total,totalTime);
				Stage secondStage = new Stage();
				
				//secondStage.initStyle(StageStyle.UNDECORATED);
		        secondStage.setScene(new Scene(root));
		        secondStage.setTitle("Results");
		        secondStage.show();
			}		
		});
		
		btnReset.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				resetNodes();
			}		
		});
		
		btnShortPath.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				longPath = true;
				//resetNodes();
				try {
					if(vertexPath.isEmpty())
					{
						addToPath(root, listButtons.get(0));
					}
					shortestPath(vertexPath.get(choiceNumber));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				longPath = false;

			}		
		});
		
		btnLongPath.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				longPath = true;
				//resetNodes();

				try {
					if(vertexPath.isEmpty())
					{
						addToPath(root, listButtons.get(0));
					}
					longestPath(vertexPath.get(choiceNumber));
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				longPath = false;

			}		
		});

		
		btnExpensiveAffordable.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{

				longPath = true;
				try {

					resetNodes();
					mostExpensiveAffordablePath(root, 0, "");

					StringTokenizer st = new StringTokenizer(tempMaxString);
					while(st.hasMoreTokens())
					{
						int id = Integer.parseInt(st.nextToken());

						Vertex<Choice>  vertex = getVertexByID(root,id,root);
						addToPath(vertex,searchButtonbyID(vertex.getValue().getID()));
					}
					
					if(tempMaxString.length() < 1)
					{
						//resetNodes();
						errorMessage("ERROR: There is no possible path that is affordable with the current budget");
					}

					//System.out.println("Most: "+ tempMaxTotal);
					//System.out.println("Most S : "+ tempMaxString);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				longPath = false;
				 

			}		
		});

		////
		btnToggleWeight.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				toggleWeights(weightVisible);
			}		
		});
		
		btnHelp.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				//go to error pane
				String help = "";
				help += "The time period of this graph is from Grade 1-12.\n\n";
				help += "This project is to help potential parents see how much raising a child costs.\n";
				help += "There are a number of choices a parent can make when raising their child.\n";
				help += "Such a choice could be eg Private vs Public school.\n";
				help += "This project allows the parents to make those choices, while seeing the costs (time/money)\n,";
				help += "advantages and disadvantages such a choice would have.\n";
				help += "\n";
				help += "A total of time and money dynammicaly changes as you make your choices.\n";
				help += "The choices are directional, so you have to follow the direction of the edge arrows.\n\n";
				help += "Buttons:\n";
				ErrorPane root = new ErrorPane(help, graphPane);
				Stage secondStage = new Stage();
				
				//secondStage.initStyle(StageStyle.UNDECORATED);
			    secondStage.setScene(new Scene(root));
			    secondStage.setTitle("Help");
			    secondStage.show();
			}		
		});
		
		btnToggleAdmin.setOnAction(new EventHandler <ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				if(isAdmin)
				{
					btnToggleAdmin.setStyle("-fx-background-color: RED");
					isAdmin = false;
				}else
				{
					btnToggleAdmin.setStyle("-fx-background-color: GREEN");
					isAdmin = true;
				}
				
				resetNodes();
			}		
		});
		
		btnAdd.setOnAction(new EventHandler <ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{	
				
				
				Vertex<Choice> ct1 = GraphPane.getParent(endVertice);
				ArrayList<Vertex<Choice>> ct2 = GraphPane.getChildren(ct1);


				UpdateOccured();
				if(ct2.size() == 1)
				{
					modifygraph.addNewNodes(ct2.get(0));
				}else
				{
					modifygraph.addNewNode(new Vertex(new Choice()),ct2);
				}
	
				
				graph = modifygraph.getGraph();
				UpdateOccured();

				//add selection to path
				//closes old window

				graphPane.getScene().getWindow().hide();
				
				//opens main menu
				
				graphPane.setVisible(true);

				Stage secondStage = new Stage();
				
				//secondStage.initStyle(StageStyle.UNDECORATED);
				GraphPane root = new GraphPane();
		        secondStage.setScene(new Scene(root));
		        secondStage.show();
		        
				weightVisible = 0;
				toggleWeights(weightVisible);
			}			
		});
		
		
	}
	
	public static void goBack()
	{
		
		//if choice exists
		if(choiceNumber > -1)
		{
			//go back one on button
			Button button = choicePath.pop();
			button.setTextFill(null);
			button.setStyle("-fx-background-color: BLACK");

			//go back on 
			Vertex<Choice> vertex = vertexPath.get(choiceNumber);
			vertex.getValue().setDisplayed(false);
			vertex.getValue().setVisited(false);
			
			//now get rid of button and vertex from list
			vertexPath.remove(choiceNumber);
			//choicePath.remove(choiceNumber);
			
			total -= vertex.getWeight();
			totalTime -= vertex.getValue().getTotalTime();
			txtTotal.setText("Money: R"+total+"\nTime: "+totalTime);
			
			choiceNumber--;
			
			
			
			
		}else
		{
			errorMessage("ERROR: There is no node selected to undo.");
			return;
		}

	}
	
	public static void addToPath(Vertex<Choice> elem,Button button)
	{
		if(longPath)
		{	
		}else
		if(!canAfford(elem.getWeight()))
		{
			errorMessage("ERROR: You cannot afford this choice.");
			return;
		}
		

		elem.getValue().setVisited(true);
        button.setTextFill(Color.BLUE);
        button.setStyle("-fx-background-color: BLUE");
        
		vertexPath.add(elem);
		choicePath.push(button);
		choiceNumber++;
		
		total += elem.getWeight();
		totalTime += elem.getValue().getTotalTime();
		txtTotal.setText("Money: R"+total+"\nTime: "+totalTime+"hrs");
		
	}
	static Vertex<Choice> ssss;
	final static int nodeSize = 50;
	final static int edgeSize = 50;
	public static int numNode = 0;
	public static void addNode(int x, int y, Vertex<Choice> elem)
	{
		numNode++;
		Choice.idCounterSet(numNode);
		Button button = new Button(""+elem.getValue().getID());
		button.setShape(new Circle(10));
		button.setPrefSize(nodeSize,nodeSize);
		button.setLayoutX(x);
		button.setLayoutY(y);
		button.setTextFill(null);
		button.setStyle("-fx-background-color: BLACK");
		
		Label lblName = new Label(elem.getValue().getName());
		lblName.setLayoutX(x);
		lblName.setLayoutY(y-15);
		
		listButtons.add(button);
		
		pane.getChildren().add(lblName);
		pane.getChildren().add(button);
		

		button.setOnAction(new EventHandler <ActionEvent>()
		{	
			@Override
			public void handle(ActionEvent event) 
			{

				txtTotal.setText("Money: R"+total+"\nTime: "+totalTime+"hrs");
				
				
				if(isAdmin)
				{
					graphPane.setVisible(false);
					//opens main menu
					ssss = elem;

					AdminPane root = new AdminPane(elem, button, graphPane,modifygraph);
					Stage secondStage = new Stage();
					
					secondStage.initStyle(StageStyle.UNDECORATED);
			        secondStage.setScene(new Scene(root));
			        secondStage.setTitle("Admin");
			        secondStage.show();
					
				}else
				if(!correctPath(elem))//if parent not visited yet
				{
					//go to error pane
					errorMessage("ERROR: The graph is directed. You can only choose a child node of the last choice you made.");
					
				}else
				if(button.getTextFill() == null)//if button viable
				{
					if(!root.getValue().getVisited())
					{
						if(!root.equals(elem))
						{
							errorMessage("ERROR: The first node chosen has to be the very left one.");
							return;
						}
						
					}
					
					graphPane.setVisible(false);
					//opens main menu
					InfoPane root = new InfoPane(elem, button, graphPane);
					Stage secondStage = new Stage();
					
					secondStage.initStyle(StageStyle.UNDECORATED);
			        secondStage.setScene(new Scene(root));
			        secondStage.setTitle("Info");
			        secondStage.show();

					
				}else
				if(button.getTextFill().equals(Color.BLUE))//if buttons already been selected
				{
					//go to error pane
					errorMessage("ERROR: This option has already been selected.");
				}else
				{
					
					
				}
			}			
		});
	}
	

	
	public static void addEdge(int xP, int yP,int x, int y, Vertex<Choice> choice, int arrowPlacement)
	{
		int posChange = 30;
		int midpointX = (xP+x+2*posChange)/2;
		int midpointY = (yP+y+2*posChange)/2;
		//System.out.println(xP +"-"+yP+"-"+x+"-"+y);
		//create line/edge from start to end
		Line line = new Line(xP+posChange, yP+posChange, x+posChange, y+posChange);
		Label lblWeight = new Label(""+choice.getWeight());
		lblWeight.setLayoutX(midpointX);
		lblWeight.setLayoutY(midpointY);
		lblWeight.setTextFill(Color.RED);
		
		listWeightsC.add(lblWeight);
		
		//create directional arrow on line
		//Line arrowUp = new Line(x+15, y+25, x+30, y+30);
		//Line arrowDown = new Line(x+25, y+15, x+30, y+30);
		

			if(arrowPlacement > choice.getValue().getArrow())//upper bound
			{
				Polygon polygon = new Polygon();
		        polygon.getPoints().addAll(new Double[]{
		        		(double) (midpointX-15),(double) midpointY+10,
		        		(double) midpointX,(double) midpointY+15,
		        		(double) midpointX, (double) midpointY,
		        });

				pane.getChildren().add(polygon);
	
			}else
			if(arrowPlacement == choice.getValue().getArrow())//middle bound
			{

				Polygon polygon = new Polygon();
		        polygon.getPoints().addAll(new Double[]{
		        		(double) (midpointX-20),(double) midpointY-10,
		        		(double) midpointX,(double) midpointY,
		        		(double) midpointX-20, (double) midpointY+10,
		        });

				pane.getChildren().add(polygon);
				
			}else
			if(arrowPlacement < choice.getValue().getArrow())//lower bound
			{
				
				Polygon polygon = new Polygon();
		        polygon.getPoints().addAll(new Double[]{
		        		(double) (midpointX),(double) midpointY-20,
		        		(double) midpointX,(double) midpointY,
		        		(double) midpointX-20, (double) midpointY-10,
		        });

				pane.getChildren().add(polygon);
			}
		
		//add line to pane
		toggleWeights(weightVisible);
		pane.getChildren().add(line);
		pane.getChildren().add(lblWeight);
	}
	


	public static Integer depth(Vertex<Choice> parentPosition) 
	{
		
		//get the TreeNodes parent

		//if root = 0
		if(parentPosition == root)
		{
			return 0;
			
		}else
		{
			Vertex<Choice> parent = null;
			try 
			{
				//get parent
				List<Edge<Choice>> listE = parentPosition.getEdges();
				
				
				parent = listE.get(0).getFromVertex();

			} catch (Exception e) {
				
			}
			
			//get parents detph + 1
			return 1 + depth(parent);
			
		}
	}

	
	private static void displayNodes(Vertex<Choice> choice) throws Exception 
	{	
		
		if(choice.equals(root))
		{
			choice.getValue().setX(0);
			choice.getValue().setY((int)scrollPane.getPrefHeight()/2);
			choice.getValue().setArrow(0);
			
			addNode(0, choice.getValue().getY(), choice);
		}

		Iterator<Vertex<Choice>> iterator = getChildren(choice).iterator();
		
		while(iterator.hasNext())
		{
			Vertex<Choice> position = iterator.next();
			
			
			if(position.getValue().getDisplayed())
			{
				
				return;
				//System.out.println(position.toString() +"    ddddddd");
			}	
			else
			{
				

				//get depth which is the x axis
				int depth = depth(position);
				
				
				if(getChildren(position).size() == 0)
				{
					endVertice = position;
				}
				//this is how many children the parent of this child, has 
				double numChild = getNumFellowChildren(position);
	
				//this is the # in order of when the child was added to a parent
				int childNum = position.getValue().getChildNum();
				
				//the above two values are used here to determine the Y value of the node/choice
				if(numChild <= 1)
				{
					position.getValue().setY(root.getValue().getY());
					position.getValue().setArrow(0);
				}
				else
				if(numChild/2 >= childNum || childNum==1)//in lower bound of loop
				{
					
					//set above parent
					position.getValue().setY(getParent(position).getValue().getY()-200/childNum);
					position.getValue().setArrow(-1);
					
				}else
				if(numChild/2 == childNum || numChild/2 == childNum-0.5)//at centre of loop
				{
					//set same as parent Y axis
					position.getValue().setY(getParent(position).getValue().getY());
					position.getValue().setArrow(0);
	
				}else
				{
					if((numChild+1-childNum) <= 0)//gets rid of neg num
					{
						//set below parent
						position.getValue().setY((int)(getParent(position).getValue().getY()+200/(-numChild+1+childNum)));
						position.getValue().setArrow(1);
					}else
					{
						//set below parent
						position.getValue().setY((int)(getParent(position).getValue().getY()+200/(numChild+1-childNum)));
						position.getValue().setArrow(1);
					}
				}
						
				position.getValue().setX(depth*100);
				
				//add nodes
				
				addNode(position.getValue().getX(), position.getValue().getY(),position);
				position.getValue().setDisplayed(true);
				

			}
			
			displayNodes(position);

		}
	}
	
	public static Vertex<Choice> getParent(Vertex<Choice> child)
	{
		List<Edge<Choice>> listC = child.getEdges();
		for(Edge<Choice> pp : listC)
		{

			if(child.getValue().getID() != pp.getFromVertex().getValue().getID())
			{
				return pp.getFromVertex();
			}
		}
		return null;
	}
	
	public static int getNumFellowChildren(Vertex<Choice> child)
	{
		Vertex<Choice> parent = getParent(child);
		return getNumChildren(parent);
		/*
		//calc num child
		List<Edge<Choice>> listC = parent.getEdges();

		int numChild = 0;
		for(Edge<Choice> pp : listC)
		{
			
			if(parent.getValue().getID() != pp.getToVertex().getValue().getID())
			{
				System.out.println("parent  " +pp.getToVertex().getValue().getID());
				numChild++;
			}
		}
		System.out.println(numChild +"  " +child.getValue().getID());
		System.out.println("-----");
		return numChild;*/
		
	}
			
	private static boolean correctPath(Vertex<Choice> newChoice)
	{
		if(choiceNumber == -1) { return true;}
		if(vertexPath.get(choiceNumber).pathTo(newChoice))
		{
			return true;
		}
		
		return false;
	}
	
 	private static void displayEdge(Vertex<Choice> choice) throws Exception 
	{

		Iterator<Vertex<Choice>> iterator = getChildren(choice).iterator();

		while(iterator.hasNext())
		{	
			Vertex<Choice> position = iterator.next();
			
			if(!position.getValue().getEdgeDisplayed())
			{

				Iterator<Vertex<Choice>> iteratorC = getChildren(position).iterator();
	
				//to add root
				if(choice == root)
				{
	
	
					addEdge(position.getValue().getX(),position.getValue().getY(),choice.getValue().getX(),choice.getValue().getY(),position,choice.getValue().getArrow());
				}
				while(iteratorC.hasNext())
				{		
	
					Vertex<Choice> c = iteratorC.next();
			 		//ed++;
			 		//System.out.println("n:"+ed+"    id" + c.getValue().getID() + "     pID:" +position.getValue().getID());
					addEdge(c.getValue().getX(),c.getValue().getY(),position.getValue().getX(),position.getValue().getY(),c,position.getValue().getArrow());
				}
				
				position.getValue().setEdgeDisplayed(true);
			}else
			{
				return;
			}
			displayEdge(position);
			
		}
			
		
		
	}
	
	
	private static void printChoices()
	{
		for(Vertex<Choice> p : vertexPath)
		{
			
			System.out.println(p.getValue().toString() + "\n");
		}
	}
	


	
	private static void shortestPath(Vertex<Choice> lastPosition) throws Exception
	{
		if(getNumChildren(lastPosition) == 0)
		{
			return;
		}

		Iterator<Vertex<Choice>> iterator = getChildren(lastPosition).iterator();
		Vertex<Choice> minPosition = iterator.next();
		
		double minWeight = minPosition.getWeight();
		
		while(iterator.hasNext())
		{
			Vertex<Choice> childPosition = iterator.next();
			
			if(childPosition.getWeight() < minWeight)
			{
				minPosition = childPosition;
				minWeight = minPosition.getWeight();
			}

		}
		
		addToPath(minPosition,searchButtonbyID(minPosition.getValue().getID()));
		shortestPath(minPosition);
		
	}
	
	private static int getNumChildren(Vertex<Choice> vertex)
	{
		return getChildren(vertex).size();
		/*List<Edge<Choice>> listC = vertex.getEdges();

		int numChild = 0;
		for(Edge<Choice> pp : listC)
		{
			if(vertex.getValue().getID() != pp.getToVertex().getValue().getID())
			{

				numChild++;
			}
		}
		
		return numChild;*/
	}
	
	public static ArrayList<Vertex<Choice>> getChildren(Vertex<Choice> vertex)
	{
		ArrayList<Vertex<Choice>> children = new ArrayList<Vertex<Choice>>();
		List<Edge<Choice>> listC = vertex.getEdges();

		for(Edge<Choice> pp : listC)
		{
			if(vertex.getValue().getID() != pp.getToVertex().getValue().getID() && pp.getToVertex().getValue().getDeleted() == false)
			{
				//this checks if not empty, then gets rid of any duplicates by return child 
				//the duplicates occur due to the edges going to and from a verice.
				if(!children.isEmpty())
					if(pp.getToVertex() == children.get(0))
						return children;


				children.add(pp.getToVertex());

			}
		}
		return children;
	}
	
	
	private static void longestPath(Vertex<Choice> lastPosition) throws Exception
	{
		if(getNumChildren(lastPosition) == 0)
		{
			return;
		}

		Iterator<Vertex<Choice>> iterator = getChildren(lastPosition).iterator();
		
		Vertex<Choice> maxPosition = iterator.next();
		double maxWeight = maxPosition.getWeight();
		
		while(iterator.hasNext())
		{
			Vertex<Choice> childPosition = iterator.next();
			
			if(childPosition.getWeight() > maxWeight)
			{
				maxPosition = childPosition;
				maxWeight = maxPosition.getWeight();
			}

		}
		
		addToPath(maxPosition,searchButtonbyID(maxPosition.getValue().getID()));
		longestPath(maxPosition);
		
	}
	
	
	private static Button searchButtonbyID(int id)
	{
		for(Button b : listButtons)
		{
			if(Integer.parseInt(b.getText()) == (id))
			{
				return b;
			}
		}
		return null;
	}
	
	private static boolean canAfford(double newAmount)
	{
		double budget = 0;
		try
		{
			 budget = Double.parseDouble(txtBudget.getText());
		}catch(Exception e)
		{
			System.out.println("err");
		}
		
		if((total+newAmount) > budget)
		{
			return false;
		}
		
		return true;
	}
	

	

	static String tempMaxString = "";
	static double tempMaxTotal = 0;
	
	private static void mostExpensiveAffordablePath(Vertex<Choice> choice,double tempTotal, String tempString) throws Exception
	{
		Iterator<Vertex<Choice>> iterator = getChildren(choice).iterator();


		tempTotal += choice.getWeight();
		tempString += choice.getValue().getID() + " ";
		//end of path
		if(getNumChildren(choice) == 0)
		{

			if(canAfford(tempTotal))
			{
				if(tempTotal > tempMaxTotal)
				{
					tempMaxString = tempString;
					tempMaxTotal = tempTotal;
				}
				//check if new highest
			}
		}
		

		
		while(iterator.hasNext())
		{
			

			//counts at which child you are currently at	
			Vertex<Choice> position = iterator.next();
			mostExpensiveAffordablePath(position, tempTotal,tempString);
			
		}
		
	}
	
	
	private static Vertex<Choice> getVertexByID(Vertex<Choice> choice, int id, Vertex<Choice> found) throws Exception
	{
		Iterator<Vertex<Choice>> iterator = getChildren(choice).iterator();


		
		while(iterator.hasNext())
		{
			//counts at which child you are currently at	
			Vertex<Choice> position = iterator.next();
			
			if(position.getValue().getID() == id)
			{
				found = position;
			}
			
			found = getVertexByID(position, id, found);
			
		}
		return found;
		
	}
	
	private static void resetNodes()
	{
		int size = vertexPath.size();
		tempMaxString = "";
		tempMaxTotal = 0;
		for(int i = 0; i < size; i++)
		{
			goBack();
		}
	}
	
	private static void errorMessage(String error)
	{
		//go to error pane
		ErrorPane root = new ErrorPane(error, graphPane);
		Stage secondStage = new Stage();
		
		//secondStage.initStyle(StageStyle.UNDECORATED);
	    secondStage.setScene(new Scene(root));
	    secondStage.setTitle("Error");
	    secondStage.show();
	}
	
	private static void toggleWeights(int isVisible)
	{
		if(isVisible == 0)//invisible
		{
			btnToggleWeight.setStyle("-fx-background-color: RED");
			for(Label l : listWeightsC)
			{
				l.setVisible(false);
			}
			
			weightVisible++;
		}else
		if(isVisible == 1)//money weights visible
		{
			btnToggleWeight.setStyle("-fx-background-color: GREEN");
			for(Label l : listWeightsC)
			{
				l.setVisible(true);
			}
			weightVisible--;
		}	
	}
	
	public void UpdateOccured()
	{
		
		resetNodes();
		Iterator<?> x = modifygraph.getGraph().getVertices().iterator();

		while(x.hasNext())
		{
			
			Vertex<Choice> p = (Vertex<Choice>) x.next();
			p.getValue().setVisited(false);
			p.getValue().setDisplayed(false);
			p.getValue().setEdgeDisplayed(false);
			
		}
		
		
		total = 0;
		totalTime = 0;

		listButtons.clear();	
		choiceNumber = -1;
		longPath = false;
		isAdmin = false;
		weightVisible = 1;// 0 none - 1 wieghts
		listWeightsC.clear();

		tempMaxString = "";
		tempMaxTotal = 0;

	//	static ArrayList<Vertex<Choice>> crudVertices = new ArrayList<Vertex<Choice>>();
		//static ArrayList<Vertex<Choice>> crudEdges = new ArrayList<Vertex<Choice>>();

		
		
	}
	
	private static void x(Vertex<Choice> choice) throws Exception
	{
		Iterator<Vertex<Choice>> iterator = getChildren(choice).iterator();


		choice.getValue().setVisited(false);
		choice.getValue().setDisplayed(false);
		choice.getValue().setEdgeDisplayed(false);
		

		
		while(iterator.hasNext())
		{
			

			//counts at which child you are currently at	
			Vertex<Choice> position = iterator.next();
			x(position);
			
		}
		
	}
	

}
