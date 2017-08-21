package com.stock.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("restriction")
public class MailUtil {
	private static Properties props = null;
	private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	static {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
		props.put("mail.smtp.auth", "true");
		
	}
	private final static String FromMail = "verification@moni0.com";
	private final static String FromMailPassword = "vwvo1234VWVO";

	public static boolean checkEmail(String email) {
		if (email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
			return true;
		}
		return false;
	}

	public static void sendMail(String reciever,String content) {
		/**
		 * 发送带附件的html格式邮件
		 */
		MimeMessage msg = null;
		try {
			Session mailSession = Session.getDefaultInstance(props);
	
			msg = createMimeMessage(mailSession, reciever, content);
			Transport transport = mailSession.getTransport();
			transport.connect(FromMail, FromMailPassword);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("邮件发送成功...");
	}

	private static MimeMessage createMimeMessage(Session session, String reciever, String content)
			throws UnsupportedEncodingException, MessagingException {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(FromMail, "MONI虚拟世界", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO,
				new InternetAddress(reciever, reciever, "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject("MONI绑定邮箱确认邮件", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(content, "text/html;charset=UTF-8");
		
		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	

}
