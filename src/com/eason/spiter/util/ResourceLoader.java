package com.eason.spiter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ResourceLoader {
	
	private static final Logger logger = Logger.getLogger(ResourceLoader.class);
	
	private static String CONF_FILE = "res.properties";
	
	private static Properties pro = null;
	
	public synchronized static Properties getResurces(){
		if(pro == null){
			pro = openConfigureFile(); 
			logger.info("the information from res.properties is:" + pro);
		}
		return pro;
	}
	
	private static Properties openConfigureFile() {
		InputStream in = null;
		pro = new Properties();
		try {
			in = ResourceLoader.class.getClassLoader().getResourceAsStream(CONF_FILE);
			pro.load(in);
		} catch (Exception e) {
			logger.error("couldn't load properties file, please check res.properties .", e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.warn("couldn't close the inputstream!", e);
				}
			}
		}
		return pro;
	}
	
}