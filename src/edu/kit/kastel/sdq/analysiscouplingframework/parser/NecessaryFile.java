package edu.kit.kastel.sdq.analysiscouplingframework.parser;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class NecessaryFile extends NecessaryID {

	public NecessaryFile(String id, String path) {
		super(id);
		super.setPath(path);
	}
	
	public NecessaryFile(String id) {
		super(id);
	}
	
	public void assignPath(String path) {
		super.setPath(path);
	}
	
	public boolean doesExist() {
		return (this.hasPath())? (new File(this.getPath())).exists() : false;
	}
	
	public void copyToFolder(NecessaryFile other) {
		if(other.isFolder()){
			File fileToCopy = new File(this.getPath());
			File dest = new File(other.getPath());
			try {
				FileUtils.copyFileToDirectory(fileToCopy, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copyFromFolder(NecessaryFile other) {
		if(other.isFolder()) {
			String filename = FilenameUtils.getName(this.getPath());
			try {
				FileUtils.copyFile(new File(other.getPath() + "/" + filename), new File(this.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isFolder() {
		return (this.hasPath())? (new File(this.getPath())).isDirectory() : false;
	}
}