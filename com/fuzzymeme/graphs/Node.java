package com.fuzzymeme.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node {

	private final String id;
	private Map<String, Arc> idToOutArcMap = new HashMap<>();
	private Map<String, Arc> idToInArcMap = new HashMap<>();
	
	public Node(final String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
		
	public void addToArc(String arcId, Arc newArc) {
		idToOutArcMap.put(arcId, newArc);
	}

	public Arc getToArc(String arcId) {
		return idToOutArcMap.get(arcId);
	}

	public Collection<Arc> toArcs() {
		return idToOutArcMap.values();
	}
	
	public void removeOutArc(Arc arc) {
		idToOutArcMap.remove(arc.getId());
	}
	
	public void addFromArc(String arcId, Arc newArc) {
		idToInArcMap.put(arcId, newArc);
	}

	public Arc getFromArc(String arcId) {
		return idToInArcMap.get(arcId);
	}

	public Collection<Arc> fromArcs() {
		return idToInArcMap.values();
	}

	public void removeInArc(Arc arc) {
		idToInArcMap.remove(arc.getId());
	}

	@Override
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(" Arcs: out{");
		for (Arc arc : idToOutArcMap.values()) {
			buffer.append("arc ").append(arc.getId()).append(":to node").append(arc.getToNodeId()).append(", ");
		}
		buffer.append("} to{");
		for (Arc arc : idToInArcMap.values()) {
			buffer.append("arc ").append(arc.getId()).append(":from node").append(arc.getFromNodeId()).append(", ");
		}
		buffer.append("}");
		return "nodeId: " + id + buffer.toString();
	}
	
	
}
