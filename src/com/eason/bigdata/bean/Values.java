package com.eason.bigdata.bean;

public class Values {
	
	private int counter;
	
	private int gloubleCounter;
	
	private double termFrequency;
	
	private double iTermFrequency;
	
	private double importantVal;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public int getGloubleCounter() {
		return gloubleCounter;
	}

	public void setGloubleCounter(int gloubleCounter) {
		this.gloubleCounter = gloubleCounter;
	}

	public double getTermFrequency() {
		return termFrequency;
	}

	public void setTermFrequency(double termFrequency) {
		this.termFrequency = termFrequency;
	}

	public double getiTermFrequency() {
		return iTermFrequency;
	}

	public void setiTermFrequency(double iTermFrequency) {
		this.iTermFrequency = iTermFrequency;
	}

	public double getImportantVal() {
		return importantVal;
	}

	public void setImportantVal(double importantVal) {
		this.importantVal = importantVal;
	}
	
	public void calculateWI() {
		this.setImportantVal(this.getTermFrequency() * this.iTermFrequency);
	}
}