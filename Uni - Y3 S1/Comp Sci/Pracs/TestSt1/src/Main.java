
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Create a URL object
        URL royalRoadURL = new URL("https://www.royalroad.com/fiction/16946/azarinth-healer");
 
        //Open a connection to the URL
        URLConnection conn = royalRoadURL.openConnection();
 
        //Get an input stream for reading
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
 
        //Read the contents of the URL
        String inputLine;
        StringBuilder sb = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
 
        //Close the stream
        in.close();
 
        //Create a Swing GUI
        JFrame frame = new JFrame("Royal Road Fiction");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JEditorPane editorPane = new JEditorPane("text/html", sb.toString());
        editorPane.setEditable(false);
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        editorPane.setPage(e.getURL());
                    } catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(editorPane);
        frame.add(scrollPane);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}