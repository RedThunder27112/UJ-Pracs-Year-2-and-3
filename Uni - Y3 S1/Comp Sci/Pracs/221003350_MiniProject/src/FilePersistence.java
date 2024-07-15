import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class FilePersistence 
{
	File file = new File("graph.data");
	Vertex<Choice> root;
	public FilePersistence()
	{
		
	}
	
	public void saveFile(Graph graph, Vertex<Choice> vertex)
	{
		try(ObjectOutputStream obs = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file))))
		{
			obs.writeObject(graph);
			obs.writeObject(vertex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Graph readGraph()
	{
		try(ObjectInputStream obs = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))))
		{
			Graph graph = (Graph) obs.readObject();
			root = (Vertex<Choice>) obs.readObject();
			return graph;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vertex<Choice> getRoot()
	{
		return root;
	}

}
