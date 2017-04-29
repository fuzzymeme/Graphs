package com.fuzzymeme.graphs;

import static org.junit.Assert.*;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test_AddingANode_RecordsTheNode() {
		
		Graph<String, String> graph = new Graph<>();
		String id = graph.addNode("bread");
		
		assertNotNull(graph.getNode(id));
		assertEquals(1, graph.getNodeCount());
		assertEquals(0, graph.getArcCount());
		assertEquals("bread", graph.getNodePayload(id));
	}
			
	@Test
	public void test_AddingTwoNode_RecordsTheNodes() {
		
		Graph<String, String> graph = new Graph<>();
		String breadId = graph.addNode("bread");
		String flourId = graph.addNode("flour");
		
		assertNotNull(graph.getNode(breadId));
		assertNotNull(graph.getNode(flourId));

		assertEquals(2, graph.getNodeCount());
		assertEquals(0, graph.getArcCount());
		
		assertEquals("bread", graph.getNodePayload(breadId));
		assertEquals("flour", graph.getNodePayload(flourId));
	}
	
	@Test
	public void test_AddingAStringArc_RecordsTheArc() {
		
		Graph<String, String> graph = new Graph<>();
		String breadId = graph.addNode("bread");
		String flourId = graph.addNode("flour");
		String bakeArcId = graph.addArc(breadId, "bake", flourId);
		 		
		assertEquals("bake", graph.getArcPayload(bakeArcId));

		assertEquals(breadId, graph.getArc(bakeArcId).getFromNodeId());
		assertEquals(flourId, graph.getArc(bakeArcId).getToNodeId());		
	}

	@Test
	public void test_AddingAnIntegerArc_RecordsTheArc() {
		
		Graph<String, Integer> graph = new Graph<>();
		String breadId = graph.addNode("bread");
		String flourId = graph.addNode("flour");
		String bakeArcId = graph.addArc(breadId, 5, flourId);
		 		
		assertEquals(bakeArcId, graph.getArc(bakeArcId).getId());
		assertEquals(new Integer(5), graph.getArcPayload(bakeArcId));

		assertEquals(breadId, graph.getArc(bakeArcId).getFromNodeId());
		assertEquals(flourId, graph.getArc(bakeArcId).getToNodeId());
	}


	@Test
	public void test_FindFirstNodeByValue() {		
		Graph<String, String> graph = createSimpleGraph();			
		Node toastNode  = graph.getFirstNodeByValue("toast");		
		assertEquals("toast", graph.getNodePayload(toastNode.getId()));
	}

	@Test
	public void test_UpdatingNode_RecordsTheChange() {
		
		Graph<String, String> graph = createSimpleGraph();
		Node toastNode  = graph.getFirstNodeByValue("toast");	
		
		graph.setNode(toastNode.getId(), "Old toast");
		assertEquals("Old toast", graph.getNodePayload(toastNode.getId()));
		
		Node oldToastNode  = graph.getFirstNodeByValue("Old toast");	
		assertEquals("Old toast", graph.getNodePayload(oldToastNode.getId()));
		assertEquals(toastNode, oldToastNode);
	}

	@Test
	public void test_UpdatingArc_RecordsTheChange() {
		
		Graph<String, String> graph = createSimpleGraph();
		Arc madeByArc = graph.getArcByValue("is made with");
				
		graph.setArcPayload(madeByArc.getId(), "is made using");
		assertEquals("is made using", graph.getArcPayload(madeByArc.getId()));
		
		Node breadNode  = graph.getFirstNodeByValue("bread");
		Arc alteredArc  = graph.getFirstArcOnNodeByValue(breadNode, "is made using");	

		assertEquals("is made using", graph.getArcPayload(madeByArc.getId()));
		assertEquals(madeByArc, alteredArc);
	}
	
	@Test
	public void test_DeletingArc_RecordsTheChange() {
		
		Graph<String, String> graph = createSimpleGraph();
		Node breadNode  = graph.getFirstNodeByValue("bread");
		Arc madeWithArc = graph.getArcByValue("is made with");
				
		graph.deleteArc(madeWithArc);
		
		Arc arcFromBread  = graph.getFirstArcOnNodeByValue(breadNode, "is made with");
		assertNull(arcFromBread);

		Node flourNode  = graph.getFirstNodeByValue("flour");
		Arc arcToFlour  = graph.getFirstArcOnNodeByValue(flourNode, "is made with");
		assertNull(arcToFlour);
	}
	
	
	
	@Test
//	public void test_AddingAListOfIntegersAsANodePayload() {
//		List<Integer> inputList = Arrays.asList(1, 3, 2);
//		
//		Graph<List<Integer>, Integer> graph = new Graph<>();
//		
//		String inputNodeId = graph.addNode(inputList);
//		
//		assertEquals(1, graph.getNodeCount());
//		assertEquals(Arrays.asList(1, 3, 2), graph.getNodePayload(inputNodeId));
//		
//		System.out.println("input:   " + inputList);
//		for(int i = 0; i < inputList.size() - 1; i++) {
//			List<Integer> flipped = flip(i, inputList);
//			System.out.println("flipped: " + flipped);
//			
//			String newNodeId = graph.addNode(flipped);
//			graph.addArc(inputNodeId, i, newNodeId);
//		}
//		
//		System.out.println("graph: " + graph);
//	}
	
	//
	// Helpers
	//
	private Graph<String, String> createSimpleGraph() {
		
		Graph<String, String> graph = new Graph<>();
		String breadId = graph.addNode("bread");
		String flourId = graph.addNode("flour");
		graph.addArc(breadId, "is made with", flourId);
		
		String toastId = graph.addNode("toast");
		graph.addArc(toastId, "is made by toasting", breadId);		
		
//		System.out.println("graph: " + graph);
		return graph;
	}
	
//	private List<Integer> flip(int flipPoint, final List<Integer> stack) {
//
//		List<Integer> flipped = new ArrayList<Integer>();
//		flipped.addAll(reverse(stack.subList(0, stack.size() - flipPoint)));
//		flipped.addAll(stack.subList(stack.size() - flipPoint, stack.size()));
//		return flipped;
//	}
//	
//	private List<Integer> reverse(List<Integer> list){
//		List<Integer> copy = new ArrayList<>(list);
//		Collections.reverse(copy);
//		return copy;
//	}

}
