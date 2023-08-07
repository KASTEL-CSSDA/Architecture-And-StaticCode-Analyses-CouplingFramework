package edu.kit.kastel.sdq.analysiscouplingframework.parser;

public abstract class NecessaryID {
	
	private String id;
	String path;

	public NecessaryID(String id) {
		this.id = id;
		this.path = "";
	}
	
	public String toString() {
		return this.id;
	}
	
	public boolean hasPath() {
		return !this.path.equals("");
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
