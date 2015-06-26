package com.eason.bigdata.clean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.eason.bigdata.loader.FileLoader;

public class FileCleaner {
	
	private static final Logger logger = Logger.getLogger(FileCleaner.class);
	
	private static int MIN = 2000;
	
	private static File makeRecordFile() throws IOException {
		File file = new File("records.txt");
		if(!file.exists()){
			if(file.createNewFile()){
				System.out.println("create file successfully!");
			}
		}
		return file;
	}
	
	private static BufferedWriter bufferedWriter = null;
	
	public static void main(String[] args) {
		File file = null;
		try {
			file = makeRecordFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		Map<String, File> map = FileLoader.listFilesToCategory();
		try {
			fis = new FileInputStream(file);
			br = new BufferedReader(isr = new InputStreamReader(fis = new FileInputStream(file)));
			
			String line = null;
			while((line = br.readLine()) != null){
				logger.info(map.get(line));
				map.remove(line);
			}
			logger.info(map.size());
			
			bufferedWriter = new BufferedWriter(new FileWriter(file,true));
			
			Iterator<Entry<String, File>> iterator = map.entrySet().iterator();
			while(iterator.hasNext() && MIN > 0){
				Entry<String, File> entry = iterator.next();
				superCleaner(entry.getValue());
				bufferedWriter.write(entry.getKey());
				bufferedWriter.newLine();
				bufferedWriter.flush();
				MIN--;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void superCleaner(File file){
		Map<String, Integer> map = new HashMap<String, Integer>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			bufferedReader = new BufferedReader(isr);
			String lineStr = null;
			while ((lineStr = bufferedReader.readLine()) != null) {
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
					}
				}
			}
			String filePath = file.getAbsolutePath().replace(".txt", ".properties");
			if(map.size() > 0){
				File theFile = new File(filePath.replace("\\", "-").replace("D:", "D:\\workspaces\\workspace\\spiter\\result\\"));
				if(!theFile.exists() && theFile.createNewFile()){
					FileWriter fw = new FileWriter(theFile);
					BufferedWriter bw = new BufferedWriter(fw);
					Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
					while(iterator.hasNext()){
						Entry<String, Integer> entry = iterator.next();
						bw.write(entry.getKey()+"="+entry.getValue());
						bw.newLine();//new line
					}
					bw.flush();
					
					bw.close();
					fw.close();
				}
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
	}
	
	
}