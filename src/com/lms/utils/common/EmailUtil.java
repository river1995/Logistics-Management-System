package com.lms.utils.common;

import java.util.*;
import javax.mail.internet.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtil {
	public void sendAuthCode(String authCode, String to, String type) throws Exception {
		Properties property = new Properties();
		property.setProperty("mail.transport.protocol", "smtp");
		property.setProperty("mail.pop.auth", "true");

		// ssl
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		property.put("mail.smtp.ssl.enable", "true");
		property.put("mail.smtp.ssl.socketFactory", sf);

		Session session = Session.getInstance(property);
		session.setDebug(true);
		Transport transport = session.getTransport();
		transport.connect(ConstantsUtil.smtpServer, ConstantsUtil.emailUser, ConstantsUtil.emailPassword);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(ConstantsUtil.emailUser, "BMS"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		StringBuilder strBuilder = new StringBuilder("亲爱的" + to + ":\n\n");
		if (type.equals("signup")) {
			message.setSubject("BMS注册验证");
			strBuilder.append("感谢注册BMS，请在操作界面输入以下验证码进行安全检验。\n\n");
		} else if (type.equals("resetpassword")) {
			message.setSubject("BMS重置密码验证");
			strBuilder.append("您正在进行重置密码操作，请在操作界面输入以下验证码进行安全检验。\n\n");
		}
		strBuilder.append("你的验证码:" + authCode + "\n\n");
		strBuilder.append("BMS敬上");
		message.setText(strBuilder.toString());
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public void sendPassword(String password, String to) throws Exception {
		Properties property = new Properties();
		property.setProperty("mail.transport.protocol", "smtp");
		property.setProperty("mail.pop.auth", "true");

		// ssl
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		property.put("mail.smtp.ssl.enable", "true");
		property.put("mail.smtp.ssl.socketFactory", sf);

		Session session = Session.getInstance(property);
		session.setDebug(true);
		Transport transport = session.getTransport();
		transport.connect(ConstantsUtil.smtpServer, ConstantsUtil.emailUser, ConstantsUtil.emailPassword);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(ConstantsUtil.emailUser, "BMS"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		StringBuilder strBuilder = new StringBuilder("Dear：" + to + ":\n\n");
		message.setSubject("BMS Login Password");
		strBuilder.append("Thank you for registering BMS,Please turn back login interface, and input your password to sign in the system"+"\n\n");

		strBuilder.append("Your password:" + password + "\n\n");
		strBuilder.append("Thank you!");
		message.setText(strBuilder.toString());
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public static void main(String[] args) throws Exception {
		EmailUtil sender = new EmailUtil();
//		sender.sendAuthCode("123asd", "jerry19950110@gmail.com", "resetpassword");
		sender.sendPassword("asdf1234", "390103721@qq.com");
	}
}
