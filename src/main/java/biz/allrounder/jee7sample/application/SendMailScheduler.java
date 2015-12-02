package biz.allrounder.jee7sample.application;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class SendMailScheduler {

	@Inject
	private DepartmentService departmentService;
	
//	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	public void sendMail() {
		departmentService.sendMailWithNewTx();
		System.out.println("sent mail!");	
	}
}
