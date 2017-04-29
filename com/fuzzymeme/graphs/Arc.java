package com.fuzzymeme.graphs;

public class Arc {
	
	private final String id;
	private String fromNodeId;
	private String toNodeId;
	
	public Arc(String id, String fromNodeId, String toNodeId) {
		super();
		this.fromNodeId = fromNodeId;
		this.toNodeId = toNodeId;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
		
	public String getFromNodeId() {
		return fromNodeId;
	}
	
	public String getToNodeId() {
		return toNodeId;
	}

	@Override
	public String toString() {
		return "arcId: " + id + ", " + fromNodeId + ":" + toNodeId;
	}
}
