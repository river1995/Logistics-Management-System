package com.lms.utils.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;



import net.sf.json.JSONObject;

public class HttpGetLogisticInfo {
	
	
	public JSONObject getLogisticByAppKSCX(String logNo){
		JSONObject object = null;
		try {
			URL url = new URL("https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/channel/data/asyncqury");		
			String params = "appid=4001&nu="+logNo;
			try {
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(true);
				urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
				urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
				urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
				urlConnection.setRequestProperty("Cache-Control", "max-age=0");
				urlConnection.setRequestProperty("Connection", "keep-alive");
				urlConnection.setRequestProperty("Cookie", "BAIDUID=1FB3B236C81AB1CC75A38DE9EDD47D82:FG=1; PSTM=1501740409; BDUSS=HUwZ1FTWU5KalR6UDlzQkQxTk9EakFEWnRLZGJVLWlkT3FNUUlYMzJYY1UwZUpaTUFBQUFBJCQAAAAAAAAAAAEAAADLerdgUml2ZXJSaXZlbgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABREu1kURLtZO; BIDUPSID=DF4D2B4FB03A4E3D4C40D1E099DFDB3E; MCITY=-194%3A; BD_UPN=12314753");
				urlConnection.setRequestProperty("Host", "sp0.baidu.com");
				urlConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
				urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
				DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
				outputStream.writeBytes(params);
				outputStream.flush();
				outputStream.close();
				
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream()));
				String inputLine = null;
				StringBuffer response = new StringBuffer();
				while (null != (inputLine = bufferedReader.readLine())) {
					response.append(inputLine);
				}

				bufferedReader.close();
				String responseBody  = response.toString();

				System.out.println("response:"+responseBody);
				JSONObject responseJSON = JSONObject.fromObject(responseBody);
				if (responseJSON.get("data") != null) {
					JSONObject dataObject = responseJSON.getJSONObject("data");
					if (dataObject.getJSONObject("info") != null) {
						object = dataObject.getJSONObject("info");
					}					
				}
				return object;
			}catch (Exception e) {
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static void main(String[] args) {
//		 HttpGetLogisticInfo getLogisticInfo = new HttpGetLogisticInfo();
//		 JSONObject object = getLogisticInfo.getLogisticByAppKSCX("402832822840");
//		 if (object != null) {
//			 System.out.println("data 不为空 +dataJSON: "+object);			
//		 }else{
//			 System.out.println("data 为空");
//		 }
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");		 
		 System.out.println(sdf.format(new Date(1511433643*1000L)));
	}
}

