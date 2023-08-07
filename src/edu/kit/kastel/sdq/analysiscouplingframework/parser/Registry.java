package edu.kit.kastel.sdq.analysiscouplingframework.parser;

import java.util.HashMap;

public class Registry {
	public static final String TEMP_SHARED_FOLDER = "TEMP_SHARED_FOLDER";
	public static final String RESULT_SHARED_FOLDER = "RESULT_SHARED_FOLDER";
	
	HashMap<String, NecessaryFile> entries;
	
	public Registry() {
		this.entries = new HashMap<String,NecessaryFile>();
	}
	
	public void initSharedFolders(String pathTempSharedFolder, String pathResultSharedFolder) {
		this.put(TEMP_SHARED_FOLDER, pathTempSharedFolder);
		this.put(RESULT_SHARED_FOLDER, pathResultSharedFolder);
	}
	
	public void put(String id, String path) {
		NecessaryFile file = new NecessaryFile(id, path);
		this.entries.put(id, file);
	}
	
	public boolean containsID(String id) {
		return this.entries.containsKey(id);
	}
	
	public NecessaryFile getFileForID(String id) {
		return this.entries.get(id);
	}
}
