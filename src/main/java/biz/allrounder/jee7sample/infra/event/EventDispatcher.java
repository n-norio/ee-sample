package biz.allrounder.jee7sample.infra.event;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import biz.allrounder.jee7sample.domain.model.MailService;
import biz.allrounder.jee7sample.domain.model.PersonWasRegisted;

@Singleton
public class EventDispatcher {

	@Inject
	private MailService mailService;
	
	@Asynchronous
	public void consumePersonEvent(@Observes PersonWasRegisted personWasRegisted) {
		mailService.sendMail();
	}
}
