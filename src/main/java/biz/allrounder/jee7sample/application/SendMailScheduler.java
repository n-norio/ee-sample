package biz.allrounder.jee7sample.application;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class SendMailScheduler {

	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void sendMail() {
		System.out.println("sent mail!");
	}
}
