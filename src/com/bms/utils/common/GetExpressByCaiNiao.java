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

public class GetExpressByCaiNiao {
	
	public void requestLogisticInfo(String postId ,String logisticType)  {
		JSONObject jsonObject = null;
		String type = "";
		try {
			URL url = new URL("https://biz.trace.ickd.cn/shentong/402832822880?mailNo=402832822880&spellName=&exp-textName=&ts=123456&enMailNo=123456789&callback=_jqjsp&_1511254521748=");
			
			try {
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setRequestProperty("m-content-md5", "205e0a9ad9ce1206fbca13ad0966c88b");
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
				System.out.println("response:"+responseBody);
				System.out.println("response:"+JSONObject.fromObject(responseBody));
				//jsonObject = JSONObject.fromObject(responseBody);
				System.out.println(responseBody);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GetExpressByCaiNiao caiNiao = new GetExpressByCaiNiao();
		caiNiao.requestLogisticInfo("402832822880", "shentong");
		//System.out.println();
	}
}
