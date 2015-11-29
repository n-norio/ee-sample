package biz.allrounder.jee7sample.infra.event;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import biz.allrounder.jee7sample.application.DepartmentService;
import biz.allrounder.jee7sample.domain.model.PersonWasRegisted;

@ApplicationScoped
public class EventDispatcher {

	@Inject
	private DepartmentService departmentService;
	
	public void consumePersonEvent(@Observes PersonWasRegisted personWasRegisted) {
		departmentService.sendMail();
	}
}
