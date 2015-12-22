package biz.allrounder.jee7sample.domain.model;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MailService {

	@Resource(name = "java:jboss/mail/Default")
	private Session session;
	
	public void sendMail() {
		try {
			System.out.println("send mail.");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("all.rounder.biz@gmail.com"));
			Address toAddress = new InternetAddress("all.rounder.biz@gmail.com");
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject("test");
			message.setText("aiueo");
			Transport.send(message);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
