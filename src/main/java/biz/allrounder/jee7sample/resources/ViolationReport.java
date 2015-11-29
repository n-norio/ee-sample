package biz.allrounder.jee7sample.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViolationReport {

	@JsonProperty
	private final String propertyPath;
	@JsonProperty
	private final String message;
	
	public ViolationReport(String propertyPath, String message) {
		this.propertyPath = propertyPath;
		this.message = message;
	}
}
