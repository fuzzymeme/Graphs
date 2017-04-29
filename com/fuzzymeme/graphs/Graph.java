package com.fuzzymeme.graphs;

import java.util.HashMap;
import java.util.Map;

public class Graph<T, E> {

	private Map<String, Node> nodes = new HashMap<>();
	private Map<String, Arc> arcs = new HashMap<>();
	
	private Map<String, E> arcIdToPayloadMap = new HashMap<>();
	private Map<String, T> nodeIdToPayloadMap = new HashMap<>();
	
	private int idCount = 0;
	
	public String addArc(String node1Id, E arcPayload, String node2Id) {		
		String arcId = "" + idCount++;
		Arc arc = new Arc(arcId, node1Id, node2Id);
		
		arcs.put(arcId, arc);
		arcIdToPayloadMap.put(arcId, arcPayload);
		
		Node fromNode = nodes.get(node1Id);
		fromNode.addToArc(arcId, arc);

		Node toNode = nodes.get(node2Id);
		toNode.addFromArc(arcId, arc);

		return arcId;
	}
	
	public void deleteArc(Arc arc) {
		arcs.remove(arc.getId());
		arcIdToPayloadMap.remove(arc.getId());
		
		Node fromNode = nodes.get(arc.getFromNodeId());
		fromNode.removeOutArc(arc);
		
		Node toNode = nodes.get(arc.getToNodeId());
		toNode.removeInArc(arc);		
	}

	public void setNode(String id, T value){
		if(!nodes.containsKey(id)){
			nodes.put(id, new Node(id));
		}
		nodeIdToPayloadMap.put(id, value);
	}
	
	public String addNode(T newPayload) {
		String graphNodeId = "" + idCount++;
		Node newNode = new Node(graphNodeId);
		
		nodes.put(graphNodeId, newNode);
		nodeIdToPayloadMap.put(graphNodeId, newPayload);
		
		return graphNodeId;
	}

	public Node getNode(String id) {
		return nodes.get(id);
	}
	
	public Node getFirstNodeByValue(T t) {
		
		for(String nodeId: nodeIdToPayloadMap.keySet()) {
			T nodePayload = nodeIdToPayloadMap.get(nodeId);
			if(nodePayload.equals(t)) {
				return nodes.get(nodeId);
			}
		}
		return null;
	}
	
	public T getNodePayload(String id) {
		return nodeIdToPayloadMap.get(id);
	}
	
	public int getNodeCount() {
		return nodes.size();
	}
	
	public Map<String, Node> getNodes() {
		return nodes;
	}
	
	public Arc getArc(String id) {
		return arcs.get(id);
	}

	public void setArcPayload(String id, E newValue) {
		arcIdToPayloadMap.put(id, newValue);
	}
	
	public E getArcPayload(String id) {
		return arcIdToPayloadMap.get(id);
	}

	// TODO should return a set and/or take a node and only look for arcs on that node
	public Arc getArcByValue(E arcValueToFind) {
		for (String arcId : arcIdToPayloadMap.keySet()) {
			E value = arcIdToPayloadMap.get(arcId);
			if(value.equals(arcValueToFind)) {
				return arcs.get(arcId);
			}
		}
		return null;
	}
	
	public Arc getFirstArcOnNodeByValue(Node node, E arcValueToFind) {
		
		for (Arc arc : node.toArcs()) {
			E value = arcIdToPayloadMap.get(arc.getId());
			if(value.equals(arcValueToFind)) {
				return arc;
			}
		}
		return null;
	}
	
	public Map<String, Arc> getArcs() {
		return arcs;
	}
	
	public int getArcCount() {
		return arcs.size();
	}
	
	public Node getFromNodeForArc(String arcId) {
		return nodes.get(arcs.get(arcId).getFromNodeId());
	}
	
	public Node getToNodeForArc(String arcId) {
		return nodes.get(arcs.get(arcId).getToNodeId());
	}
	
	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("\narcs:\n" + arcs).append("\nnodes:\n");
		for (Node node : nodes.values()) {
			buffer.append(node).append("\n");
		}
		return buffer.toString();
	}
	
	
}
