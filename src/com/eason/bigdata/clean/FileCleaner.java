package com.eason.bigdata.clean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eason.bigdata.bean.DirtyWater;
import com.eason.bigdata.bean.PureWater;
import com.eason.bigdata.loader.FileLoader;

public class FileCleaner {
	
	private static final Logger logger = Logger.getLogger(FileCleaner.class);

	public static void main(String[] args) {
		List<DirtyWater> dirtyWaters = FileLoader.listFilesToCategory();
		List<PureWater> pureWaters = new ArrayList<PureWater>();
		for (DirtyWater dirtyWater : dirtyWaters) {
			List<File> files = dirtyWater.getSubFiles();
			PureWater pureWater = new PureWater();
			pureWater.setFileAbsoultePath(dirtyWater.getFileAbsoultePath());
			for (File file : files) {
				FileInputStream fis = null;
				InputStreamReader isr = null;
				BufferedReader bufferedReader = null;
				try {
					fis = new FileInputStream(file);
					isr = new InputStreamReader(fis);
					bufferedReader = new BufferedReader(isr);
					String lineStr = null;
					while ((lineStr = bufferedReader.readLine()) != null) {
						filterTheDirty(pureWater, lineStr);
					}
				} catch (Exception e) {
					logger.error(e);
				} finally {
					try {
						bufferedReader.close();
						isr.close();
						fis.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
				
			}
			pureWaters.add(pureWater);
		}
	}

	private static PureWater filterTheDirty(PureWater pureWater, String lineStr) {
		Map<String, Integer> map = pureWater.getWords();
		String[] strs = lineStr.split(FilterRule.SPACE_STRING);
		for (String str : strs) {
			//remove . , ! etc  deal with regix only all are characters and numbers
			if(! FilterRule.SENSITIVE_WORD_LIST.contains(str.toLowerCase())  && FilterRule.REST){
				int counter = 0;
				if(map.containsKey(str.toLowerCase())){
					counter = map.get(str.toLowerCase()) + 1;
				}else {
					counter = 1;
				}
				map.put(str.toLowerCase(), counter);
				
				if(!"".equals(pureWater.getMaxFreqKey()) && counter > map.get(pureWater.getMaxFreqKey())){
					pureWater.setMaxFreqKey(str.toLowerCase());
				}
				
			}
		}
		return pureWater;
	}
}