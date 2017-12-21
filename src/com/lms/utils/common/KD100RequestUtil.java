package com.lms.utils.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class KD100RequestUtil {
	
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
			URL url = new URL("https://p.kuaidi100.com/rss/weixin/query.do");
			
			try {
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("POST");
				urlConnection.setDoOutput(true);
				urlConnection.setUseCaches(true);
				DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
				String params = "token="+ConstantsUtil.queryToken+"&com="+type+"&num="+postId;
				outputStream.writeBytes(params);
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
				jsonObject = JSONObject.fromObject(responseBody);
				System.out.println(responseBody);
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
		KD100RequestUtil caiNiao = new KD100RequestUtil();
		//System.out.println(caiNiao.requestLogisticInfo("240825063169", "顺丰"));
		System.out.println("jsonObject:"+caiNiao.requestLogisticInfo("4021788873252", "申通"));
	
		
		
	}
}
