package biz.allrounder.jee7sample.resources;

import java.lang.annotation.Annotation;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Dependent
@Logging
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {

	@AroundInvoke
    public Object invoke(final InvocationContext ctx) throws Throwable {
		System.out.println("aiueo");
		
		if (ctx.getMethod().isAnnotationPresent(Logging.class)) {
			Logging logging = ctx.getMethod().getAnnotation(Logging.class);
			System.out.println(logging.operationName());
		}
		return ctx.proceed();
	}
}
