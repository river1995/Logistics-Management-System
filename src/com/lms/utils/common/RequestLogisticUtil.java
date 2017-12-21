package com.lms.utils.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RequestLogisticUtil {
	
	
	public JSONObject requestLogisticInfo(String postId ,String logisticType)  {
		JSONObject jsonObject = null;
		String type = "";
		switch(logisticType){
		case "顺丰":
			type = "shunfeng";
			break;
		case "申通":
			type = "shentong";
			break;
		case "中通":
			type = "zhongtong";
			break;
		case "EMS":
			type = "ems";
			break;
		case "韵达":
			type = "yunda";
			break;
		case "圆通":
			type = "yuantong";
			break;
		case "百世":
			type = "huitongkuaidi";
			break;
		default :
			break;
		}
		try {
			//URL url = new URL("https://m.kuaidi100.com/query?type="+type+"&postid="+postId+"&id=1&valicode=&temp=0.026317220867324576 ");
			URL url = new URL("https://p.kuaidi100.com/mobile/mobileapi.do?method=query ");
			
			try {
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("POST");
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(true);
				JSONObject json = new JSONObject();
				//json.put("json", "%7B%0A%20%20%22t%22%20%3A%201513837755467%2C%0A%20%20%22tra%22%20%3A%20%22F4617739-7A42-47A3-AB74-9AA2911783B8%22%2C%0A%20%20%22os_version%22%20%3A%20%2210.3.2%22%2C%0A%20%20%22os_name%22%20%3A%20%22iPhone%22%2C%0A%20%20%22com%22%20%3A%20%22shentong%22%2C%0A%20%20%22num%22%20%3A%20%22402832822891%22%2C%0A%20%20%22appid%22%20%3A%20%22com.youshang.kuaidi100%22%2C%0A%20%20%22versionCode%22%20%3A%20%224.6.2%22%0A%7D");
				json.put("hash", "1585132bdf673ec21a3982cceb6f19ef");
				json.put("method", "query");
				
				JSONObject infoJSON = new JSONObject();
				infoJSON.put("t", "1513837755467");
				infoJSON.put("tra", "F4617739-7A42-47A3-AB74-9AA2911783B8");
				infoJSON.put("os_version", "10.3.2");
				infoJSON.put("os_name", "iPhone");
				infoJSON.put("com", "shentong");
				infoJSON.put("num", "402832822891");
				infoJSON.put("appid", "com.youshang.kuaidi100");
				infoJSON.put("versionCode", "4.6.2");
				
				json.put("json", infoJSON.toString());
				
				DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
				
				outputStream.writeBytes(json.toString());
				outputStream.flush();
				outputStream.close();
				
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
				String inputLine = null;
				StringBuffer response = new StringBuffer();
				while (null != (inputLine = bufferedReader.readLine())) {
					response.append(inputLine);
				}

				bufferedReader.close();
				String responseBody  = response.toString();
				jsonObject = JSONObject.fromObject(responseBody);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public static void main(String[] args) {
		RequestLogisticUtil requestUtil = new RequestLogisticUtil();
		JSONObject object= requestUtil.requestLogisticInfo("402832822891","申通");
//		JSONArray jsonArray = object.getJSONArray("data");
		System.out.println("object:"+object);
		//System.out.println(jsonArray);
	}
}
