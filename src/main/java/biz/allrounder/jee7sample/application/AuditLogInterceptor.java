package biz.allrounder.jee7sample.application;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AuditLogInterceptor {
	
	@AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        System.out.println("DefaultInterceptor before");
        Object result = context.proceed();
        System.out.println("DefaultInterceptor after");
        return result;
    }
}
