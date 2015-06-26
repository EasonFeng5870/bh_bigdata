package com.eason.bigdata.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class PureWater {
	
	//private static final Logger logger = Logger.getLogger(PureWater.class); 
	
	private String fileAbsoultePath;//each file
	
	private Map<String, Integer> words = new HashMap<String, Integer>();// Data Structure[<word, counter>]
	
	//private Map<String, Values> wordsValue = new HashMap<String, Values>();
	
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

//	public Map<String, Values> getWordsValue() {
//		return wordsValue;
//	}
//
//	public void setWordsValue(Map<String, Values> wordsValue) {
//		this.wordsValue = wordsValue;
//	}

	/**
	 * calculate every doc term frequency.
	 */
	public void calculateTF() {
		if(words != null && words.size() > 0){
			int maxFrequcy = words.get(maxFreqKey);
			Set<Entry<String, Integer>> set = words.entrySet();
			Iterator<Entry<String, Integer>> iterator = set.iterator();
			while(iterator.hasNext()){
				final Entry<String, Integer> entry = iterator.next();
				Values values = new Values();
				values.setTermFrequency(entry.getValue()*1.0/maxFrequcy);
				values.setCounter(entry.getValue());
				//wordsValue.put(entry.getKey(), values);
			}
		}
		//logger.info("the purewater for "+ this.wordsValue);
	}
	
}