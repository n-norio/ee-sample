package biz.allrounder.jee7sample.resources;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

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
	
	private static final List<Class<?>> restEasyParamClasses = Arrays.asList(
			PathParam.class,
			QueryParam.class,
			HeaderParam.class,
			MatrixParam.class,
			CookieParam.class);

	@AroundInvoke
    public Object invoke(final InvocationContext ctx) throws Throwable {
		
		if (ctx.getMethod().isAnnotationPresent(Logging.class)) {
			Logging logging = ctx.getMethod().getAnnotation(Logging.class);
			System.out.println(logging.operationName());
		}
		
		List<Type> jsonTypeList = new ArrayList<>();
		for (Parameter param: ctx.getMethod().getParameters()) {
			Annotation[] anons = param.getAnnotations();
			for (Annotation anon: anons) {
				if (restEasyParamClasses.contains(anon)) {
					continue;
				}
				jsonTypeList.add(param.getType());
			}
		}

		for (Object object: ctx.getParameters()) {
			if (jsonTypeList.contains(object.getClass())) {
				String json = new ObjectMapper().writeValueAsString(object);
				System.out.println(json);
			}
		}
		
		userId.set(req.getRemoteUser());
		return ctx.proceed();
	}
}
