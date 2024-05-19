/**
 * 
 */
package acsse.csc2a.ui;


import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.file.TelescopeFileHandler;
import acsse.csc2a.model.ETELESCOPE_TYPE;
import acsse.csc2a.model.Telescope;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**class TelescopePane - to display the file writing/saving in a GUI format see Telescope,TelescopeFileHandler classes
 * @author Ariel Sischy
 * @version P05
 */

public class TelescopePane extends StackPane
{
	Telescope objTel;
	final Pattern patternID = Pattern.compile("[A-Z]{3}[0-9]{4}");//pattern of id
	/**
	 * TelescopePane constructor - creates GUI to save/read from Telescope files
	 */
	
	public TelescopePane()
	{
		//Initialise vbox
		VBox root = new VBox();
		
		//file chooser
		FileChooser fc = new FileChooser();
				
		//directory of file
		fc.setInitialDirectory(new File("data"));
		
		
		//creates menu bar
		MenuBar menuBar = new MenuBar();
				
		//creates menu
		Menu menuFile = new Menu("File");
				
		//creates MenuItem for opening file
		MenuItem itemOpen = new MenuItem("Open");
		
		//creates MenuItem for saving file
		MenuItem itemSave = new MenuItem("Save");
		
		//add open and save item to file menu
		menuFile.getItems().addAll(itemOpen,new SeparatorMenuItem(), itemSave);
		
		//add file menu to menu bar
		menuBar.getMenus().add(menuFile);
		
		//creating labels and text output/input
		Label lblID = new Label("ID:\t");
		TextField txtID = new TextField();
		
		Label lblType = new Label("Telescope Type:\t");
		TextField txtType = new TextField();
		
		//creating grid pane to display info
		GridPane gridTel = new GridPane();
		
		gridTel.add(lblID, 0, 0);//adds id nodes to grid
		gridTel.add(txtID, 1, 0);
		
		gridTel.add(lblType, 0, 1);//adds type nodes to grid
		gridTel.add(txtType, 1, 1);
		
		
		//creates TitledPane
		TitledPane tileTel = new TitledPane("Telescope Information",gridTel);
		
		root.getChildren().add(menuBar);
		root.getChildren().add(tileTel);
		
		this.getChildren().add(root);
		this.setPrefSize(800, 600);
		
		
		
		//when open clicked
		itemOpen.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//getting readFile info
				File readFile = fc.showOpenDialog(null);
				
				//checks if file is not selected
				if(readFile == null)
				{
					System.err.println("No file has been chosen");
					return;//returns nothing
				}
				
				objTel = TelescopeFileHandler.readTelescope(readFile);//reads file
				
				//adding file data to textfields
				txtID.setText(objTel.getID());
				
				txtType.setText(objTel.getType().toString());	
			}
	
		});
		
		//when save is clicked
		itemSave.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
				//getting writeFile info
				File writeFile = fc.showOpenDialog(null);
				
				//checks if file is not selected
				if(writeFile == null)
				{
					System.err.println("No file has been chosen");
					return;//returns nothing
				}
				
				//gets text from text fields
				String tempID = txtID.getText();
				
				////here be the validation for id 
				
				StringTokenizer st = new StringTokenizer(tempID,"\t");//to seperate id and TYPE
				
				String tokenID = st.nextToken();//gets first string in line ie the id
				
				//telescope matcher
				Matcher matherTel = patternID.matcher(tokenID);
				
				//if id matches pattern
				if (matherTel.matches()) 
				{
					//do nothing if matches
				}else
				{
					System.err.println("Incorrect ID.");//if doesnt match
					return;
				}
				
					
				
				//////////
					
				String tempType = txtType.getText();
				
				//converting from text to enum basically
				if(tempType.equals("OPTICAL"))
				{
					objTel = new Telescope(tempID, ETELESCOPE_TYPE.OPTICAL);
					
				}else
				if(tempType.equals("INFRARED"))
				{
					objTel = new Telescope(tempID, ETELESCOPE_TYPE.INFRARED);
					
				}else
				{
					System.err.println("Incorrect Type");
					return;
				}
				
				
					
				
				TelescopeFileHandler.writeTelescope(objTel, writeFile);//writes to file
			}
			
		});	
	}
}
