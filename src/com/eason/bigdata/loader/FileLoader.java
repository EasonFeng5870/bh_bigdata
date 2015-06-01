package com.eason.bigdata.loader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.eason.bigdata.bean.DirtyWater;

public class FileLoader {
	
	private static final Logger logger = Logger.getLogger(FileReader.class);
	
	public static int fileCounter = 0;
	
	public static List<DirtyWater> listFilesToCategory(){
		File file = new File("D:\\Program Files (x86)\\qq\\372427166\\FileRecv\\short-text-documents\\data");
		logger.info("--" + file.getName());
		File[] files = file.listFiles();
		List<DirtyWater> dirtyWaters = new ArrayList<DirtyWater>();
		for (File subFile : files) {
			if(subFile.isDirectory()){
				File[] files2 = subFile.listFiles();
				DirtyWater d = new DirtyWater();
				d.setFileAbsoultePath(subFile.getAbsolutePath());
				d.setFile(subFile);
				d.setSubFiles(Arrays.asList(files2));
				dirtyWaters.add(d);
				fileCounter += files2.length;
			}
		}
		logger.info("all files number is: " + fileCounter);
		return dirtyWaters;
	}

}