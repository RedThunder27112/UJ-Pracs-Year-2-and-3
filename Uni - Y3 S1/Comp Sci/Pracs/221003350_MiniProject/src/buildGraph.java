import java.util.ArrayList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.TYPE;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class buildGraph
{
	//Tree<Vertex<Choice>> tree;
	Vertex<Choice> root;
	Graph graph;
	
	public buildGraph()
	{
		boolean x = true;
		//the below value never changes - this is for manually rewriting choices in the file in case many are deleted and 
		//you want to go back to the inputted ones
		if(x)
		{
			
		
			FilePersistence save = new FilePersistence();
			graph = save.readGraph();
			root = save.getRoot();
			vertices = (ArrayList<Vertex<Choice>>) graph.getVertices();
			edges = (ArrayList<Edge<Choice>>) graph.getEdges();
		
		
		}else
		{
		
		Choice c1 = new Choice();
		
		Vertex<Choice> v1 = new Vertex<Choice>(c1);
	
		
		//tree = new Tree<Vertex<Choice>>(v1);
		root = v1;
	
		
		vertices.add(root);
		


		 Vertex<Choice> a1 = addNewChild(root, new Vertex<Choice>(new Choice()));
		 Vertex<Choice> a2 = addNewChild(root, new Vertex<Choice>(new Choice()));
		 Vertex<Choice> a3 = addNewChild(root, new Vertex<Choice>(new Choice()));
		 Vertex<Choice> a4 = addNewChild(root, new Vertex<Choice>(new Choice()));
		 Vertex<Choice> a5 = addNewChild(root, new Vertex<Choice>(new Choice()));
		 
		 Vertex<Choice> b1 = addNewChild(a1, new Vertex<Choice>(new Choice()));
		 addNewChild(a2, b1);
		 addNewChild(a3, b1);
		 addNewChild(a4, b1);
		 addNewChild(a5, b1);	

		Vertex<Choice> d1 = addNewChild(b1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> d2 = addNewChild(b1, new Vertex<Choice>(new Choice()));
			
		Vertex<Choice> e1 = addNewChild(d1, new Vertex<Choice>(new Choice()));
		addNewChild(d2, e1);
			
		Vertex<Choice> f1 = addNewChild(e1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> f2 = addNewChild(e1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> f3 = addNewChild(e1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> f4 = addNewChild(e1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> f5 = addNewChild(e1, new Vertex<Choice>(new Choice()));
			
		//start sport
		Vertex<Choice> g1 = addNewChild(f1, new Vertex<Choice>(new Choice()));
		addNewChild(f2, g1);
		addNewChild(f3, g1);
		addNewChild(f4, g1);
		addNewChild(f5, g1);
		//sport choices

		Vertex<Choice> g2 = addNewChild(g1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> g3 = addNewChild(g1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> g4 = addNewChild(g1, new Vertex<Choice>(new Choice()));

		//start school lunch
		Vertex<Choice> h1 = addNewChild(g2, new Vertex<Choice>(new Choice()));
		addNewChild(g3, h1);
		addNewChild(g4, h1);
		//school lunch choices
		Vertex<Choice> h2 = addNewChild(h1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> h3 = addNewChild(h1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> h4 = addNewChild(h1, new Vertex<Choice>(new Choice()));
		
		
		//start breakfast and dinner
		Vertex<Choice> i1 = addNewChild(h2, new Vertex<Choice>(new Choice()));
		addNewChild(h3, i1);
		addNewChild(h4, i1);

		//school lunch choices
		Vertex<Choice> i2 = addNewChild(i1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> i3 = addNewChild(i1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> i4 = addNewChild(i1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> i5 = addNewChild(i1, new Vertex<Choice>(new Choice()));
		

		//start computer
		Vertex<Choice> j1 = addNewChild(i2, new Vertex<Choice>(new Choice()));
		addNewChild(i3, j1);
		addNewChild(i4, j1);
		addNewChild(i5, j1);

		//computer choices
		Vertex<Choice> j2 = addNewChild(j1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> j3 = addNewChild(j1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> j4 = addNewChild(j1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> j5 = addNewChild(j1, new Vertex<Choice>(new Choice()));
		
		//start phone
		Vertex<Choice> k1 = addNewChild(j2, new Vertex<Choice>(new Choice()));
		addNewChild(j3, k1);
		addNewChild(j4, k1);
		addNewChild(j5, k1);

		//start phone
		Vertex<Choice> k2 = addNewChild(k1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> k3 = addNewChild(k1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> k4 = addNewChild(k1, new Vertex<Choice>(new Choice()));
		Vertex<Choice> k5 = addNewChild(k1, new Vertex<Choice>(new Choice()));
		//Vertex<Choice> k = addNewChild(b1, new Vertex<Choice>(new Choice()));
		graph = new Graph(TYPE.DIRECTED,vertices, edges);
		}
		
		
	}
	

	
	static ArrayList<Vertex<Choice>> vertices = new ArrayList<Vertex<Choice>>();
	static ArrayList<Edge<Choice>> edges = new ArrayList<Edge<Choice>>();
	
	
	public Vertex<Choice> addNewChild(Vertex<Choice> parent, Vertex<Choice> child)
	{
		Edge<Choice> e = new Graph.Edge((int)child.getValue().getTotalRand(), parent,child);//check child...
		parent.addEdge(e);
		child.addEdge(e);
		child.setWeight((int)child.getValue().getTotalRand());
		child.getValue().setChildNum(getNumChildren(parent));
		vertices.add(child);
		edges.add(e);
		return child;
	}
	
	public void addNewNode(Vertex<Choice> child, ArrayList<Vertex<Choice>> parent)
	{
		if(Choice.getCounter() < 10)
		{
			Choice.idCounterSet(GraphPane.numNode);
			System.out.println(GraphPane.numNode);
		}
		
		for(Vertex<Choice> p : parent) 
		{
			Vertex<Choice> x = addNewChild(p, child);
			x.getValue().getAdvantages().clear();
			x.setWeight(0);
		}
		Graph graphX = new Graph(TYPE.DIRECTED,vertices, edges);
		graph = new Graph(graphX);
		
		FilePersistence save = new FilePersistence();
		save.saveFile(graph, root);
		
	}
	
	public void addNewNodes(Vertex<Choice> parent)
	{
		if(Choice.getCounter() < 10)
		{
			Choice.idCounterSet(GraphPane.numNode);
			System.out.println(GraphPane.numNode);
		}
		
		addNewChild(parent, new Vertex<Choice>(new Choice()));
		addNewChild(parent, new Vertex<Choice>(new Choice()));
		addNewChild(parent, new Vertex<Choice>(new Choice()));
		addNewChild(parent, new Vertex<Choice>(new Choice()));
		addNewChild(parent, new Vertex<Choice>(new Choice()));
		
		Graph graphX = new Graph(TYPE.DIRECTED,vertices, edges);
		graph = new Graph(graphX);
		
		FilePersistence save = new FilePersistence();
		save.saveFile(graph, root);
		
	}
	
	
	public Graph<?> getGraph()
	{
		return graph;
	}
	
	
	public Vertex<Choice> getRoot()
	{
		
		return root;
	}
	
	private static int getNumChildren(Vertex<Choice> vertex)
	{
		ArrayList<Vertex<Choice>> children = new ArrayList<Vertex<Choice>>();
		List<Edge<Choice>> listC = vertex.getEdges();

		for(Edge<Choice> pp : listC)
		{
			if(vertex.getValue().getID() != pp.getToVertex().getValue().getID())
			{
				children.add(pp.getToVertex());

			}
		}
		return children.size();
	}
	

	
	

}
