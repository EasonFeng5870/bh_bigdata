package com.eason.spiter.loader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.eason.spiter.dao.SpiterDao;
import com.eason.spiter.util.FileUtil;
import com.eason.spiter.util.ResourceLoader;

public class SpiterMan {
	
	private static final Logger logger = Logger.getLogger(SpiterMan.class);

	private static HttpClient httpClient = null;
	
	private static String mHost = ResourceLoader.getResurces().getProperty("mHost");
	private static String mainPath = ResourceLoader.getResurces().getProperty("mainPath");
	private static String mainFilePath = ResourceLoader.getResurces().getProperty("mainFilePath");
	private static String mTarget = ResourceLoader.getResurces().getProperty("mTarget");
	
	private static Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) {
		ResourceLoader.getResurces();
		try {
			 HttpResponse httpResponse = getHttpResponse(mHost, mTarget);
			 if(httpResponse.getStatusLine().getStatusCode() == 200){
				 
				 HttpEntity entity = httpResponse.getEntity();
				 String html = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset().toString());
				 FileUtil.writingToFile(html, mainFilePath);
				 List<String> lists = getsubList(html, mHost);
				 if(lists != null && lists.size() > 0)
					 getResult(lists);
			 }
			 if("true".equalsIgnoreCase(ResourceLoader.getResurces().getProperty("isusedao"))){
				 SpiterDao.updateDB(map);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static HttpResponse getHttpResponse(String host, String url) {
		httpClient = new DefaultHttpClient();
		HttpHost httpHost = new HttpHost(host);
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		try {
			return httpClient.execute(httpHost,httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void getResult(List<String> lists) throws Exception {
		for (String urlStr : lists) {
			URL url = new URL(urlStr);
			if("shop.58.com".equalsIgnoreCase(url.getHost())){
				getDataFrom58Shop(url);
			}else if("bj.58.com".equalsIgnoreCase(url.getHost())){
				getDataFrombjShop(url);
			}
			
			Thread.sleep(3000);
		}
		
	}

	private static void getDataFrombjShop(URL url) {
		String urlStr = url.toString();
		StringBuffer stringBuffer = new StringBuffer("\nthe URL is: " + urlStr +"\n");
		HttpResponse httpResponse = getHttpResponse(url.getHost(), urlStr);
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			try {
				String html = EntityUtils.toString(httpResponse.getEntity());
				String filePath = mainPath + urlStr.substring(urlStr.lastIndexOf(".")+1);
				FileUtil.writingToFile(html, filePath);
				
				Document doc = Jsoup.parse(html);
				Elements elements = doc.getElementsByAttributeValue("class", "col detailPrimary mb15");
				for (Element element : elements) {
					if(element.html().indexOf("span") > 0){
						stringBuffer.append("The title is: " + element.text() +"\n");
					}
				}
				Elements els = doc.getElementsByAttributeValue("class", "nPric");
				for (Element element : els) {
					stringBuffer.append("the price is: " + element.text() + "\n");
				}
				map.put(urlStr, stringBuffer.toString());
				logger.info(stringBuffer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void getDataFrom58Shop(URL url) {
		String urlStr = url.toString();
		StringBuffer stringBuffer = new StringBuffer("\nthe URL is: " + urlStr +"\n");
		HttpResponse httpResponse = getHttpResponse(url.getHost(), urlStr);
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			try {
				String html = EntityUtils.toString(httpResponse.getEntity());
				String filePath = mainPath + urlStr.substring(urlStr.lastIndexOf(".")+1);
				FileUtil.writingToFile(html, filePath);
				
				Document doc = Jsoup.parse(html);
				Elements elements = doc.getElementsByAttributeValue("class", "hc");
				for (Element element : elements) {
					if(element.html().indexOf("span") > 0){
						stringBuffer.append("The title is: " + element.text() +"\n");
					}
				}
				Elements els = doc.getElementsByAttributeValue("id", "minprice");
				for (Element element : els) {
					stringBuffer.append("the price is: " + element.text() + "\n");
				}
				map.put(urlStr, stringBuffer.toString());
				logger.info(stringBuffer.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static List<String> getsubList(String html, String host) {
		List<String> list = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements eles = doc.getElementsByAttributeValue("class", "t");
		for (Element element : eles) {
			list.add(element.attr("href"));
		}
		return filterDirtyUrls(host, list);
	}

	private static List<String> filterDirtyUrls(String host, List<String> urls) {
		List<String> filteredUrls = new ArrayList<String>();
		if(urls != null && urls.size() > 0){
			for (String url : urls) {
				String type = url.substring(url.lastIndexOf(".") +1 );
				if("html".equalsIgnoreCase(type) || "shtml".equalsIgnoreCase(type)){
					if(url.indexOf("http") == -1){
						url = "http://" + host + url; 
					}
					filteredUrls.add(url);
				}
			}
			return filteredUrls;
		}
		return null;
	}

}