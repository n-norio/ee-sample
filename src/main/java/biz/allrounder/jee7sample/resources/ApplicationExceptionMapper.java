package biz.allrounder.jee7sample.resources;

import javax.persistence.NoResultException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import biz.allrounder.jee7sample.exception.ProjectApplicationException;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ProjectApplicationException> {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(ProjectApplicationException exception) {
		if (exception.getCause() != null) {
			if (exception.getCause() instanceof NoResultException) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON_TYPE).build();
			}
		}
		
		return Response.status(Status.BAD_REQUEST).entity("error").build();
	}

}
