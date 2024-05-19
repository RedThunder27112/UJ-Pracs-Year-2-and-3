import java.awt.EventQueue;
import java.awt.Frame;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.WindowConstants;

import UI.CommandLine;
import UI.VideoPlayer;
import javafx.embed.swing.JFXPanel;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main 
{

	private JFrame frmVidPlayer;
	private JFXPanel panelVideo;
	private MediaPlayer player;
	private int i = 0;
	
	public static void main(String[] args) 
	{
		
	    EventQueue.invokeLater(new Runnable() 
	    {
	    	
	        public void run() 
	        {
	            try 
	            {
	            	CommandLine.execCMD();//executes command line
	        		System.setProperty("java.awt.headless", "true");
	                Main mainWindow = new Main();//try to creating an instance of the program, and display it
	                mainWindow.frmVidPlayer.setVisible(true);
	                
	            } catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        
	    });
	}
	
	public Main() 
	{
	    initProgram();//runs the program
	}
	

	private void initProgram() 
	{
		
		//creating kiosk
        frmVidPlayer = new JFrame();
        
        frmVidPlayer.setUndecorated(true);
        
        frmVidPlayer.setAlwaysOnTop(true);
     
        frmVidPlayer.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        frmVidPlayer.setExtendedState(Frame.MAXIMIZED_BOTH);

        frmVidPlayer.setVisible(true);


        //creating panel which video will be on
        panelVideo = new JFXPanel();

        panelVideo.setSize(10, 10);
	    
        panelVideo.setBounds(4, 4, frmVidPlayer.getWidth(), frmVidPlayer.getHeight());
        
        frmVidPlayer.getContentPane().add(panelVideo);

        
        //adding button to play - will need to go or be edited
	    JButton btnNewButton = new JButton("Play");
	    //btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
	    btnNewButton.setBounds(20, 20, 20, 20);
	    frmVidPlayer.getContentPane().add(btnNewButton);
	
	    
	    btnNewButton.addActionListener(new ActionListener() 
	    {
	  
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {//msut go
				
				if(i == 0)
				{
					getVideo("test2.mp4");				
					i = 1;
				}else
				if(i == 1)
				{
					if(player != null)
					{
						player.pause();
						player.setOnEndOfMedia(null);
						
					}
					getQR("test.jpeg");
					i++;
				
					
				}else
				if(i == 2)
				{
					getVideo("test.mp4");
					i++;	
				}else	
				{
					frmVidPlayer.dispose();//temporary
				}
				
			}
	    });
	}
	
	private void getVideo(String fName)//fName is the videos name
	{
	    File fileVideo = new File("data",fName);//gets video name/location
	    Media video = new Media(fileVideo.toURI().toString());//convert file to video
	    
	    player = new MediaPlayer(video);
	    player.play();//start video
	    
	    VideoPlayer videoPlayer = new VideoPlayer(player);
	    StackPane root = new StackPane();
	    Scene scene = new Scene(root);
	    scene.setRoot(videoPlayer);
	    
	    //SET SCENE
	    panelVideo.setScene(scene);
	    
	    //loop video
	    player.setCycleCount(MediaPlayer.INDEFINITE);

	    
	}
	
	private void getQR(String fName)
	{
		
	    File fileImage = new File("data",fName);//gets image
	    
	    Image image = new Image(fileImage.toURI().toString());//convert file to v
	    
	    ImageView imageView = new ImageView(image);

	    imageView.setFitHeight(panelVideo.getHeight());
	    imageView.setFitWidth(panelVideo.getWidth());

	    //Setting the preserve ratio of the image view 
	    imageView.setPreserveRatio(true);  
	    
	    StackPane root = new StackPane(imageView);
	    
	    Scene scene = new Scene(root);
	    
	    panelVideo.setScene(scene);//is an image now
	    
	}

}
