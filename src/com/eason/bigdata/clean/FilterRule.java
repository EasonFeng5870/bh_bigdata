package com.eason.bigdata.clean;

import java.util.ArrayList;
import java.util.List;

public class FilterRule {
	
	public static void main(String[] args) {
		//System.out.println(SENSITIVE_WORD_LIST.contains("this"));
		String str = "[[&&52(++)asdf";
		System.out.println(str.replaceAll("[&()\\[\\]\\d\\++]", ""));
		
		int a = 1;
		int b = 3;
		double c = a*1.0/b;
		System.out.println(c);
		System.out.printf("%.6f",c);
	}
	
	public static final int LENGTH_AT_LEAST = 3;
	public static final String SPLITER = " ";
	public static final String regx = "[&()\\[\\]\\d|\\+!~@#$%^&*-_=?/\\:;'\"{}<,>.�`+]";
	
	public static List<String> FLUSHED_WORD_LIST = new ArrayList<String>();
	public static final boolean REST = true;
	
	static {
		FLUSHED_WORD_LIST.add("you");
		FLUSHED_WORD_LIST.add("she");
		FLUSHED_WORD_LIST.add("them");
		FLUSHED_WORD_LIST.add("they");
		FLUSHED_WORD_LIST.add("her");
		FLUSHED_WORD_LIST.add("your");
		FLUSHED_WORD_LIST.add("his");
		
		FLUSHED_WORD_LIST.add("one");
		FLUSHED_WORD_LIST.add("two");
		FLUSHED_WORD_LIST.add("three");
		FLUSHED_WORD_LIST.add("four");
		FLUSHED_WORD_LIST.add("five");
		FLUSHED_WORD_LIST.add("six");
		FLUSHED_WORD_LIST.add("seven");
		FLUSHED_WORD_LIST.add("eight");
		FLUSHED_WORD_LIST.add("nine");
		FLUSHED_WORD_LIST.add("ten");
		FLUSHED_WORD_LIST.add("hunred");
		FLUSHED_WORD_LIST.add("hunreds");
		FLUSHED_WORD_LIST.add("thousand");
		
		FLUSHED_WORD_LIST.add("then");
		FLUSHED_WORD_LIST.add("while");
		FLUSHED_WORD_LIST.add("after");
		FLUSHED_WORD_LIST.add("before");
		FLUSHED_WORD_LIST.add("what");
		FLUSHED_WORD_LIST.add("who");
		FLUSHED_WORD_LIST.add("when");
		FLUSHED_WORD_LIST.add("where");
		FLUSHED_WORD_LIST.add("don't");
		FLUSHED_WORD_LIST.add("doesn't");
		FLUSHED_WORD_LIST.add("does");
		FLUSHED_WORD_LIST.add("this");
		FLUSHED_WORD_LIST.add("that");
		FLUSHED_WORD_LIST.add("from");
		FLUSHED_WORD_LIST.add("the");
		FLUSHED_WORD_LIST.add("yeah");
		FLUSHED_WORD_LIST.add("was");
		FLUSHED_WORD_LIST.add("were");
		FLUSHED_WORD_LIST.add("for");
		FLUSHED_WORD_LIST.add("boy");
		FLUSHED_WORD_LIST.add("girl");
		FLUSHED_WORD_LIST.add("miss");
		FLUSHED_WORD_LIST.add("mr.");
		FLUSHED_WORD_LIST.add("dad");
		FLUSHED_WORD_LIST.add("son");
		FLUSHED_WORD_LIST.add("father");
		FLUSHED_WORD_LIST.add("mom");
		FLUSHED_WORD_LIST.add("mother");
		FLUSHED_WORD_LIST.add("did");
		FLUSHED_WORD_LIST.add("didn't");
		FLUSHED_WORD_LIST.add("are");
		FLUSHED_WORD_LIST.add("it's");
		FLUSHED_WORD_LIST.add("will");
		FLUSHED_WORD_LIST.add("ever");
		FLUSHED_WORD_LIST.add("just");
		FLUSHED_WORD_LIST.add("and");
	}
	
}
