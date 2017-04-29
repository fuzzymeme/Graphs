package com.fuzzymeme.graphs;

public class GraphCreator {

	public GraphCreator() throws Exception{
								
		Graph<String, String> graph = new Graph<>();
		String breadId = graph.addNode("bread");
		String flourId = graph.addNode("flour");
		String bakingId = graph.addNode("baking");
		graph.addArc(breadId, "made with", flourId);
		graph.addArc(breadId, "made by", bakingId);
		
		System.out.println("Graph: " + graph);
	}
	
	public void createGraph(String input){
		
//		System.out.println("Parsed:" + input);
				

	}
	
	public static void main(String[] args) {
		
		try {
			GraphCreator graphCreator = new GraphCreator();
			
			//graphCreator.createGraph("A court ruled that the regulator did not have the power to sanction the firm");
			//graphCreator.createGraph("Where is my brain");
			graphCreator.createGraph("my brain is in my head");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
