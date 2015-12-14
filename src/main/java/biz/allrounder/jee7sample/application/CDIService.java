package biz.allrounder.jee7sample.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import biz.allrounder.jee7sample.domain.model.session.SessionStart;
import biz.allrounder.jee7sample.domain.model.session.UserSession;

@ApplicationScoped
@Transactional
public class CDIService {

	@Inject
	private UserSession session;
	
	@SessionStart
	public void test() {
		System.out.println(session.hashCode());
		System.out.println(session.sessionId());
		System.out.println(session.sessionId().hashCode());
		System.out.println(session.hashCode());
		System.out.println(session.sessionId());
		System.out.println(session.sessionId().hashCode());
	}
}
