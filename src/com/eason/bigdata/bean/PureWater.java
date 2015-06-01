package com.eason.bigdata.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;


public class PureWater {
	
	private static final Logger logger = Logger.getLogger(PureWater.class); 
	
	private String fileAbsoultePath;//each file
	
	private Map<String, Integer> words = new HashMap<String, Integer>();// Data Structure[<word, counter>]
	
	private Map<String, Double> wordsTF = new HashMap<String, Double>();
	
	private String maxFreqKey = "";

	public String getFileAbsoultePath() {
		return fileAbsoultePath;
	}

	public void setFileAbsoultePath(String fileAbsoultePath) {
		this.fileAbsoultePath = fileAbsoultePath;
	}

	public String getMaxFreqKey() {
		return maxFreqKey;
	}

	public void setMaxFreqKey(String maxFreqKey) {
		this.maxFreqKey = maxFreqKey;
	}

	public Map<String, Integer> getWords() {
		return words;
	}

	public void setWords(Map<String, Integer> words) {
		this.words = words;
	}

	public Map<String, Double> getWordsTF() {
		return wordsTF;
	}

	public void setWordsTF(Map<String, Double> wordsTF) {
		this.wordsTF = wordsTF;
	}

	/**
	 * calculate every doc term frequency.
	 */
	public void calculateTF() {
		if(words.size() > 0){
			int maxFrequcy = words.get(maxFreqKey);
			Set<Entry<String, Integer>> set = words.entrySet();
			Iterator<Entry<String, Integer>> iterator = set.iterator();
			while(iterator.hasNext()){
				Entry<String, Integer> entry = iterator.next();
				double d = entry.getValue()/maxFrequcy;
				wordsTF.put(entry.getKey(), d);
			}
			set.iterator();
		}
		logger.info("the purewater for "+ this.wordsTF);
	}
	
}