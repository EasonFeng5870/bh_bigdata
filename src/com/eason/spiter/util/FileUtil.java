package com.eason.spiter.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	public static void writingToFile(String htmlStr, String filePath) {
		File file = new File(filePath);
		Boolean fileBoolean = false;
		try {
			fileBoolean = file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 if(fileBoolean){
			try {
				touchFile(htmlStr,file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	}
	
	private static void touchFile(String html,File file) throws Exception {
		FileOutputStream  fileoutput = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(fileoutput);
		buffer.write(html.getBytes());
		buffer.flush();
		buffer.close();
	}

}
