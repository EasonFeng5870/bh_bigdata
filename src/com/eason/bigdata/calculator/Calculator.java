package com.eason.bigdata.calculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class Calculator {
	
	private static final Logger logger = Logger.getLogger(Calculator.class);

	public static void main(String[] args) {
		
		try {
			List<Properties> propertiesList = calculateTF();
			for (Properties pro : propertiesList) {
				String maxKey = "";
				long maxCounter = 0;
				Iterator<Entry<Object, Object>> iterator = pro.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<Object, Object> entry = iterator.next();
					
				}
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static List<Properties> calculateTF() throws IOException {
		File file = new File("result");
		List<Properties> prolist = new ArrayList<Properties>();
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				Properties properties = new Properties();
				properties.load(new FileInputStream(files[i]));
				prolist.add(properties);
			}
		}
		logger.info("the properties file number is:" + prolist.size());
		return prolist;
	}

}
