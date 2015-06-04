package com.eason.bigdata.calculator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.eason.bigdata.bean.PureWater;
import com.eason.bigdata.bean.Values;
import com.eason.bigdata.clean.FileCleaner;
import com.eason.bigdata.util.LogarithmUtil;

public class Calculator {
	
	private static final Logger logger = Logger.getLogger(Calculator.class);

	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		
		List<PureWater> pureWaters = FileCleaner.cleaner();
		int N = pureWaters.size();
		
		for (int i = 0; i < pureWaters.size(); i++) {
			PureWater pureWater = pureWaters.get(i);
			Map<String, Values> map = pureWater.getWordsValue();
			Iterator<Entry<String, Values>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				final Entry<String, Values> entry = iterator.next();
				String str = entry.getKey();
				long exsitCounter = 0L;
				for (int j = 0; j < pureWaters.size(); j++) {
					if(pureWaters.get(j).getWordsValue().get(str) != null){
						exsitCounter++;
					}
				}
				entry.getValue().setiTermFrequency(LogarithmUtil.log(N*1.0 / exsitCounter));
				entry.getValue().calculateWI();
			}
		}

		logger.error("spent about " + (System.currentTimeMillis() - startTime)/1000 + "s to finish the job!");
	}

}
