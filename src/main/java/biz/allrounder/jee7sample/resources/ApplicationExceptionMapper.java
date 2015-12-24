package biz.allrounder.jee7sample.resources;

import javax.persistence.NoResultException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;

import biz.allrounder.jee7sample.exception.ProjectApplicationException;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Exception exception) {
		if (exception instanceof JsonProcessingException) {
			ViolationReport report = new ViolationReport("", "不正なリクエストが送信されました。");
			return Response.status(Status.BAD_REQUEST).entity(report).build();
		}
		if (exception instanceof ProjectApplicationException) {
			if (exception.getCause() != null) {
				if (exception.getCause() instanceof NoResultException) {
					return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON_TYPE).build();
				}
			}
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("システムエラーが発生しました。").build();
	}

}
