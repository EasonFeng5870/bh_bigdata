package com.eason.bigdata.bean;

import java.io.File;
import java.util.List;

public class DirtyWater {
	
	private String fileAbsoultePath;
	
	private File file;
	
	private List<File> subFiles;

	public String getFileAbsoultePath() {
		return fileAbsoultePath;
	}

	public void setFileAbsoultePath(String fileAbsoultePath) {
		this.fileAbsoultePath = fileAbsoultePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<File> getSubFiles() {
		return subFiles;
	}

	public void setSubFiles(List<File> subFiles) {
		this.subFiles = subFiles;
	}

}
