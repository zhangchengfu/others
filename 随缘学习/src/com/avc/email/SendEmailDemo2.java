package com.avc.email;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

class SeUglyBO{
	private String host = "smtp.qq.com"; // 定义发送用户帐号密码

	private String from = "2880349990@qq.com";//发送邮件的邮箱

	private String user = "2880349990@qq.com"; // 邮箱的用户

	private String password = "kagmknqykijedghb"; // 企业QQ授权码
	/**
	 * @param to  收件人
	 * @param title  主题
	 * @param content 内容
	 * @return boolean
	 * @throws IOException
	 */
	public boolean send(String to, String title, String content) throws IOException {
		//System.out.println(to);
		boolean ioke = true;
		try {
			System.out.println(to+"======"+title+"======"+content);
			// 架设smtp
			Properties pro = new Properties();
			pro.put("smtp.qq.com", host);
			pro.put("smtp.qq.com", "true");// ******
			pro.put("mail.smtp.ssl.enable", "true");

			Session session = Session.getDefaultInstance(pro);
			session.setDebug(true); // 是否在控制台打出语句

			MimeMessage message = new MimeMessage(session); // 定义从哪个邮箱到哪个邮箱的地址和内容
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{
								new InternetAddress(to)});

			message.setSubject(title);
			message.setText("Java程序的邮箱测试2");//邮件的正文内容
			message.saveChanges();

			Transport tran = session.getTransport("smtp"); // 通过SMTP效验用户，密码等进行连接
			tran.connect(host, user, password);
			tran.sendMessage(message, message.getAllRecipients());
			tran.close();

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ioke = false;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ioke = false;
		}

		return ioke;
	}
}
	public class SendEmailDemo2 {
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			SeUglyBO se=new SeUglyBO();
			try {

				se.send("1547959612@qq.com","这是主题","这是设么");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}