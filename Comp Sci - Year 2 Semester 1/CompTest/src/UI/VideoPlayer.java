package UI;
import javafx.scene.layout.StackPane;
import javafx.scene.CacheHint;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class VideoPlayer extends BorderPane
{
	
	public VideoPlayer(MediaPlayer player)
	{
		
		
		
		MediaPlayer vidPlayer = player;
		setStyle("-fx-background-color: black;");
		MediaView viewPlayer = new MediaView(vidPlayer);
		StackPane panePlayer = new StackPane();
		
		viewPlayer.setCache(true);

		viewPlayer.setCacheHint(CacheHint.SPEED);
    

    panePlayer.getChildren().add(viewPlayer);
    panePlayer.setStyle("-fx-background-color: black;");//black bars for video
    
    //viewPlayer.fitHeightProperty().bind(panePlayer.heightProperty());
    //viewPlayer.fitWidthProperty().bind(panePlayer.widthProperty());
    

    viewPlayer.setPreserveRatio(true);
    setCenter(panePlayer);
	}

}

