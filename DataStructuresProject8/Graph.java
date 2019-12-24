package TheFinalCode;

import java.util.*;

public class Graph {

	private Map<String,List<Edge>> graphData = new HashMap<String,List<Edge>>();
	private boolean isDirected;
	private boolean isWeighted;
	
	public Graph(boolean isDirected, boolean isWeighted) {
	  this.isDirected = isDirected;
	  this.isWeighted = isWeighted;
	}
	
	// this method will add edges to the graph based on a string.
	// this string is in the form "from,to" 
	// each pair of adjacent nodes should be separated by a semicolon.
	public void addEdges(String edgeList) {
	  for (String edgePair : edgeList.split(";")) {
	    String[] edges = edgePair.split(",");
	    if (edges.length == 2) {
	      addEdge(edges[0], edges[1]);
	    }
	  }
	}
	
	// add an edge from one node to another; if this is a weighted graph this will throw an exception
	public void addEdge(String from, String to) {
	  if (isWeighted) {
	    throw new UnsupportedOperationException("Graph is weighted.");
	  }
	  addEdge(from, to, 1);
	}
	
	// add an edge from one node to another; 
	// this method will handle reversing the edge for undirected graphs
	public void addEdge(String from, String to, int weight) {
		List<Edge> connections = graphData.get(from);
		if (connections == null) {
			connections = new LinkedList<Edge>();
			graphData.put(from, connections);
		}
		connections.add(new Edge(to, weight));
		if (!isDirected) {
		  connections = graphData.get(to);
		  if (connections == null) {
		    connections = new LinkedList<Edge>();
		    graphData.put(to, connections);
		  }
		  connections.add(new Edge(from, weight));
		}
	}
	
	// determine if you can travel directly from "from" to "to"
	public boolean isAdjacent(String from, String to) {
	  List<Edge> edges = graphData.get(from);
	  if (edges != null) {
	    for (Edge edge : edges) {
	      if (edge.adjacentNode.equals(to)) {
	        return true;
	      }
	    }
	  }
	  return false;
	}
	
	// return the weight between "from" and "to"
	// if nodes are not adjacent, return -1
	public int getWeight(String from, String to) {
	  List<Edge> edges = graphData.get(from);
	  if (edges != null) {
	    for (Edge edge : edges) {
	      if (edge.adjacentNode.equals(to)) {
	        return edge.weight;
	      }
	    }
	  }
	  return -1;
	}
	
	// represent the weight and an adjacent node
	private class Edge {
	  private int weight = 1;
	  private String adjacentNode;
	  
	  public Edge(String adjacentNode, int weight) {
	    this.adjacentNode = adjacentNode;
	  }

	  @Override
	  public String toString(){
		  return adjacentNode;
	  }
	}
	
	public static void main(String[] args) {
	  String nodes = "A,B;B,C;C,D;D,E;A,K;K,E;A,F;F,G;G,H;H,I;I,J;J,E";
	  Graph graph = new Graph(false, false);
	  graph.addEdges(nodes);
	  System.out.println(graph.getShortestPath("A", "E")); // should print [A, K, E]
	}
	
	public List<String> getShortestPath(String from, String to) {
		List<String> returner;

		List<Edge> myEdges = graphData.get(from);
		String finalString = recursiveShortest(from, to, from);
		returner = Arrays.asList(finalString);

		return returner;
	}

	private String recursiveShortest(String from, String to, String lastTimeOnRecursion){
		//Found it!
		if(from.equals(to)) {
			return lastTimeOnRecursion;
		}
		else{
			List<String> niceList = new ArrayList<>();

			//For each of the edges, if it's already in the list, don't go down it
			List<Edge> edges = graphData.get(from);

			String myListString = "{";
			for(int i = 0; i < edges.size(); i ++){
				myListString += edges.get(i) + ", ";
			}

			for(int i = 0; i < edges.size(); i ++){
				String node = edges.get(i).adjacentNode;
				if(!lastTimeOnRecursion.contains(node)){
					String gotBack = recursiveShortest(node, to, lastTimeOnRecursion + node);
					niceList.add(gotBack);
				}
			}

			//Now if niceList is empty, we want null
			if(niceList.size() == 0){
				return null;
			}
			else{
				//return the shortest string in the list
				String shortest = niceList.get(0);
				for(int i = 0; i < niceList.size(); i++){
					if(niceList.get(i).length() < shortest.length()){
						shortest = niceList.get(i);
					}
				}

				return shortest;
			}
		}
	}
}