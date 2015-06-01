package com.eason.bigdata.clean;

import java.util.ArrayList;
import java.util.List;

public class FilterRule {
	
	public static final int LENGTH_AT_LEAST = 3;
	public static final String SPLITER = " ";
	
	public static final List<String> SENSITIVE_WORD_LIST = new ArrayList<String>();
	public static final boolean REST = true;
	
	{
		SENSITIVE_WORD_LIST.add("you");
		SENSITIVE_WORD_LIST.add("she");
		SENSITIVE_WORD_LIST.add("them");
		SENSITIVE_WORD_LIST.add("they");
		
		SENSITIVE_WORD_LIST.add("one");
		SENSITIVE_WORD_LIST.add("two");
		SENSITIVE_WORD_LIST.add("three");
		SENSITIVE_WORD_LIST.add("four");
		SENSITIVE_WORD_LIST.add("five");
		SENSITIVE_WORD_LIST.add("six");
		SENSITIVE_WORD_LIST.add("seven");
		SENSITIVE_WORD_LIST.add("eight");
		SENSITIVE_WORD_LIST.add("nine");
		SENSITIVE_WORD_LIST.add("ten");
		SENSITIVE_WORD_LIST.add("hunred");
		SENSITIVE_WORD_LIST.add("hunreds");
		SENSITIVE_WORD_LIST.add("thousand");
		
		SENSITIVE_WORD_LIST.add("then");
		SENSITIVE_WORD_LIST.add("while");
		SENSITIVE_WORD_LIST.add("after");
		SENSITIVE_WORD_LIST.add("before");
		SENSITIVE_WORD_LIST.add("what");
		SENSITIVE_WORD_LIST.add("who");
		SENSITIVE_WORD_LIST.add("when");
		SENSITIVE_WORD_LIST.add("where");
		SENSITIVE_WORD_LIST.add("don't");
		SENSITIVE_WORD_LIST.add("this");
		SENSITIVE_WORD_LIST.add("that");
		SENSITIVE_WORD_LIST.add("from");
		SENSITIVE_WORD_LIST.add("the");
		SENSITIVE_WORD_LIST.add("yeah");
		SENSITIVE_WORD_LIST.add("was");
		SENSITIVE_WORD_LIST.add("were");
		SENSITIVE_WORD_LIST.add("for");
		SENSITIVE_WORD_LIST.add("boy");
		SENSITIVE_WORD_LIST.add("girl");
		SENSITIVE_WORD_LIST.add("miss");
		SENSITIVE_WORD_LIST.add("mr.");
		SENSITIVE_WORD_LIST.add("dad");
		SENSITIVE_WORD_LIST.add("son");
		SENSITIVE_WORD_LIST.add("father");
		SENSITIVE_WORD_LIST.add("mom");
		SENSITIVE_WORD_LIST.add("mother");
		SENSITIVE_WORD_LIST.add("did");
		SENSITIVE_WORD_LIST.add("are");
		SENSITIVE_WORD_LIST.add("it's");
		SENSITIVE_WORD_LIST.add("will");
		SENSITIVE_WORD_LIST.add("ever");
		SENSITIVE_WORD_LIST.add("just");
	}
	
}
