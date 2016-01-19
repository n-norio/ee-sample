package biz.allrounder.jee7sample.resources;

import java.lang.reflect.Parameter;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

@Interceptor
@Dependent
@Logging
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {

	@Inject
	private UserId userId;
	@Inject
	private HttpServletRequest req;	

	@AroundInvoke
    public Object invoke(final InvocationContext ctx) throws Throwable {
		System.out.println("aiueo");
		
		if (ctx.getMethod().isAnnotationPresent(Logging.class)) {
			Logging logging = ctx.getMethod().getAnnotation(Logging.class);
			System.out.println(logging.operationName());
		}
		
		for (Object object: ctx.getParameters()) {
			if (object.getClass().getAnnotation(JsonObject.class) != null) {
				String json = new ObjectMapper().writeValueAsString(object);
				System.out.println(json);
			}
		}
		
		userId.set(req.getRemoteUser());
		return ctx.proceed();
	}
}
