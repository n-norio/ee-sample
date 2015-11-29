package biz.allrounder.jee7sample.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper;
import org.jboss.resteasy.api.validation.Validation;

@Provider
public class ValidationExceptionMapper extends ResteasyViolationExceptionMapper {

	private final ResourceBundle resouceBundle = ResourceBundle.getBundle("ItemNames");
	
	@Override
	protected Response buildViolationReportResponse(ResteasyViolationException exception, Status status) {
		ResponseBuilder builder = Response.status(status);
		builder.header(Validation.VALIDATION_HEADER, "true");
		builder.type(MediaType.APPLICATION_JSON_TYPE);

		List<ViolationReport> reports = new ArrayList<>();

		for (List<ResteasyConstraintViolation> violations: exception.getViolationLists()) {
			for (ResteasyConstraintViolation violation: violations) {
				reports.add(new ViolationReport(jsonPath(violation.getPath()), 
						itemName(violation.getPath()) + violation.getMessage()));
			}
		}
		builder.entity(reports);
		return builder.build();
	}

	private String jsonPath(String path) {
		return path.substring(path.indexOf(".", path.indexOf(".")+1)+1);
	}
	private String itemName(String path) {
		return resouceBundle.getString(
				path.substring(path.indexOf(".", 2)+1).replaceAll("\\[.*\\]", ""));
	}
	
}
