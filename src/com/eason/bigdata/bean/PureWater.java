package com.eason.bigdata.bean;

import java.util.HashMap;
import java.util.Map;


public class PureWater {
	
	private String fileAbsoultePath;
	
	private Map<String, Integer> words = new HashMap<String, Integer>();
	
	private String maxFreqKey = "";

	public String getFileAbsoultePath() {
		return fileAbsoultePath;
	}

	public void setFileAbsoultePath(String fileAbsoultePath) {
		this.fileAbsoultePath = fileAbsoultePath;
	}

	public Map<String, Integer> getWords() {
		return words;
	}

	public void setWords(Map<String, Integer> words) {
		this.words = words;
	}

	public String getMaxFreqKey() {
		return maxFreqKey;
	}

	public void setMaxFreqKey(String maxFreqKey) {
		this.maxFreqKey = maxFreqKey;
	}
	
}