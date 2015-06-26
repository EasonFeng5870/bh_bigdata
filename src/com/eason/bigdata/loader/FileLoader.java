package com.eason.bigdata.loader;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class FileLoader {
	
	private static final Logger logger = Logger.getLogger(FileReader.class);
	
	public static int fileCounter = 0;
	
	private static final Map<String, File> map = new HashMap<String, File>();
	
	public static Map<String, File> listFilesToCategory(){
		File file = new File("D:\\myplugins\\bigdata\\short-text-documents\\data");
		File[] files = file.listFiles();
		for (File subFile : files) {
			if(subFile.isDirectory()){
				for (File everyFile : subFile.listFiles()) {
					map.put(everyFile.getAbsolutePath(), everyFile);
				}
			}
		}
		logger.info("there are " + fileCounter + " files in ----" + file.getAbsolutePath());
		logger.info(map.size());
		return map;
	}
	
}