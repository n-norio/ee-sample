package biz.allrounder.jee7sample.resources;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import biz.allrounder.jee7sample.application.LoggingService;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

	@Inject
	private LoggingService loggingService;
	@Inject
	private HttpServletRequest req;
	
	public LoggingFilter() {
		System.out.println("constructor");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getHeaders());
		System.out.println(requestContext.getSecurityContext().getUserPrincipal());
		System.out.println(req.getRemoteUser());
		loggingService.write();
	}

}
