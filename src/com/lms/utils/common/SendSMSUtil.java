package com.lms.utils.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;

import net.sf.json.JSONObject;

public class SendSMSUtil {
	
	public void sendSms(){
		GlobalParams globalParams = new GlobalParams();
		globalParams.setRequestPath("http://api01.monyun.cn:7901/sms/v2/std");
		globalParams.setNeedLog(0);
		int rs1 = ConfigManager.setGlobalParams(globalParams);
		int rs2 = ConfigManager.setAccountInfo(ConstantsUtil.userid, ConstantsUtil.SMSPWD, 1, "127.0.0.1", "127.0.0.1", null, null);
		System.out.println("rs1:"+rs1+"rs2:"+rs2);
		boolean isKeepAlive = false;
		// 实例化短信处理对象
		SmsSendConn smsSendConn = new SmsSendConn(isKeepAlive);
		singleSend(smsSendConn, "E101NR");

	}
	
	public static void singleSend(SmsSendConn smsSendConn, String userid) {
		try {
			// 参数类
			Message message = new Message();
			// 设置用户账号 指定用户账号发送，需要填写用户账号，不指定用户账号发送，无需填写用户账号
			message.setUserid(userid);
			// 设置手机号码 此处只能设置一个手机号码
			message.setMobile("15805902431");
			// 设置内容
			message.setContent("测试短信");
			// 设置扩展号
			//message.setExno("11");
			// 用户自定义流水编号
			//message.setCustid("20160929194950100001");
			// 自定义扩展数据
			//message.setExdata("abcdef");
			// 业务类型
			//message.setSvrtype("SMS001");
			// 返回的流水号
			StringBuffer returnValue = new StringBuffer();
			// 返回值
			int result = -310099;
			// 发送短信
			result = smsSendConn.singleSend(message, returnValue);
			// result为0:成功
			if (result == 0) {
				System.out.println("单条发送提交成功！");
				System.out.println(returnValue.toString());
			}
			// result为非0：失败
			else {
				System.out.println("单条发送提交失败,错误码：" + result);
			}
		} catch (Exception e) {
			// 异常处理
			e.printStackTrace();
		}
	}
				
		

	public static void main(String[] args) {
		SendSMSUtil sendSMSUtil = new SendSMSUtil();
		sendSMSUtil.sendSms();
	}
	
}
