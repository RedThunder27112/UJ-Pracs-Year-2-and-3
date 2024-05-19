package UI;


import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class MediaPane extends BorderPane
{
	private File mediaFile;
	public MediaPane()
	{
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem openItem = new MenuItem("Open");
		
		fileMenu.getItems().add(openItem);
		menuBar.getMenus().add(fileMenu);
		
		openItem.setOnAction(e ->
		{
			FileChooser fc = new FileChooser();
			fc.setTitle("Pick a file");
			fc.setInitialDirectory(new File("data"));
			mediaFile = fc.showOpenDialog(null);
			
			if(mediaFile != null)
			{
				constructMediaPlayer();
			}
		});
		
		this.setTop(menuBar);
	}

	private void constructMediaPlayer() 
	{
		Media media = null;
		
		try
		{
			media = new Media(mediaFile.toURI().toURL().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			MediaView mediaView = new MediaView(mediaPlayer);
			mediaView.setFitHeight(500);
			mediaView.setFitWidth(500);
			this.setCenter(mediaView);
			
			Button play = new Button("Play");
			play.setPrefHeight(500);
			play.setOnAction(e ->{
				mediaPlayer.play();
			});
			this.setBottom(play);
			
		} catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	

}
