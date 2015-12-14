package biz.allrounder.jee7sample.application;

import java.util.UUID;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import biz.allrounder.jee7sample.domain.model.session.SessionStart;
import biz.allrounder.jee7sample.domain.model.session.UserSession;

@Interceptor
@SessionStart
public class SessionStartInterceptor {

	@Inject
	private UserSession session;
	
	@AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
		session.sessionId(UUID.randomUUID().toString());
		session.token(UUID.randomUUID().toString());
		System.out.println(session.hashCode());
		
		Object result = ic.proceed();
		
		System.out.println(session.hashCode());
		return result;
	}
}
