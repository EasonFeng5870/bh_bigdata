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
	
	public static List<PureWater> cleaner(){
		List<DirtyWater> dirtyWaters = FileLoader.listFilesToCategory();
		List<PureWater> pureWaters = new ArrayList<PureWater>();
		for (DirtyWater dirtyWater : dirtyWaters) {
			List<File> files = dirtyWater.getSubFiles();
			
			for (File file : files) {
				PureWater pureWater = new PureWater();
				pureWater.setFileAbsoultePath(file.getAbsolutePath());
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
						logger.error("failed to close the readers",e);
					}
				}
				logger.debug(pureWater.getFileAbsoultePath() + "\t\t\t\t" +pureWater.getWords().size());
				logger.debug(pureWater.getMaxFreqKey()+"|"+pureWater.getWords().get(pureWater.getMaxFreqKey()));
				pureWater.calculateTF();
				pureWater.setWords(null);
				pureWaters.add(pureWater);
			}
		}
		return pureWaters;
	}

	private static PureWater filterTheDirty(PureWater pureWater, String lineStr) {
		Map<String, Integer> map = pureWater.getWords();
		String[] strs = lineStr.split(FilterRule.SPLITER);
		for (String str : strs) {
			
			str = str.trim().replaceAll(FilterRule.regx, "");
			
			if(str != null && !"".equals(str)){
				if("".equals(str)) continue;
			}
			if(str != null && !"".equals(str) && str.length() >=3 && ! FilterRule.FLUSHED_WORD_LIST.contains(str.toLowerCase())){//length equal or over 3 and it didn't in the meaningless words
				
				int counter = 0;
				if(map.containsKey(str.toLowerCase())){
					counter = map.get(str.toLowerCase()) + 1;
				} else {
					counter = 1;
				}
				map.put(str.toLowerCase(), counter);
				
				if("".equals(pureWater.getMaxFreqKey())){
					pureWater.setMaxFreqKey(str.toLowerCase());
				}
				if(!"".equals(pureWater.getMaxFreqKey()) && counter > map.get(pureWater.getMaxFreqKey())){
					pureWater.setMaxFreqKey(str.toLowerCase());
				}
				
				logger.debug("the word is:" + str.toLowerCase() +"|" + counter +"|"+ pureWater.getMaxFreqKey());
			}
		}
		return pureWater;
	}
}