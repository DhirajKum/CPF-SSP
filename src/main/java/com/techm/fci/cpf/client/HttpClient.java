package com.techm.fci.cpf.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClient {

	private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
	private static final String USER_AGENT = "Mozilla/5.0";

	/**
	 * Do a HTTP Get Call
	 * @param pageUrl
	 */
	public static String getHttpPage(String pageUrl) {
		logger.info("Page Url = " + pageUrl);
		StringBuffer responseBuffer = new StringBuffer("");
		try {
			URL url = new URL(null,pageUrl,new sun.net.www.protocol.http.Handler());
			//Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.248.225.76",7778));
		//	HttpURLConnection con = (HttpURLConnection) url.openConnection(webProxy);
			HttpURLConnection con =(HttpURLConnection)url.openConnection();
			/*con.setRequestMethod("GET");
			con.setReadTimeout(120000);
			con.setConnectTimeout(120000);*/
		//	con.setRequestProperty("User-Agent", USER_AGENT);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String str;
			while ((str = in.readLine()) != null) {
				responseBuffer.append(str);
			}
			in.close();
		} catch (Exception e) {
			logger.error("Problem with Url = " + pageUrl);
			logger.info("Some Exception has occured don't process this response");
			responseBuffer = new StringBuffer("");
		}
		return responseBuffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getHttpPage("http://bulkpush.mytoday.com/BulkSms/SingleMsgApi?feedid=1879&To=919810503680&Text=Hello%20Vipin"));
	}

}
