package com.bms.utils.common;

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
			URL url = new URL("https://m.kuaidi100.com/query?type="+type+"&postid="+postId+"&id=1&valicode=&temp=0.026317220867324576 ");
			
			try {
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(true);
				DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
				
				//outputStream.writeBytes(params);
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
		JSONObject object= requestUtil.requestLogisticInfo("614134452661","顺丰");
		JSONArray jsonArray = object.getJSONArray("data");
		System.out.println("object:"+object);
		System.out.println(jsonArray);
	}
}
