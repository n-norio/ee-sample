package biz.allrounder.jee7sample.infra.event;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import biz.allrounder.jee7sample.domain.model.MailService;
import biz.allrounder.jee7sample.domain.model.PersonWasRegisted;

@Singleton
public class EventDispatcher {

	@Inject
	private MailService mailService;
	
	@Asynchronous
	@Lock(LockType.READ)
	public void consumePersonEvent(@Observes(during = TransactionPhase.AFTER_SUCCESS) PersonWasRegisted personWasRegisted) {
		mailService.sendMail();
	}
}
