package biz.allrounder.jee7sample.domain.model.session;

import javax.enterprise.context.RequestScoped;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@RequestScoped
public class UserSession {

	private String sessionId;
	private String token;
	

	public void sessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void token(String token) {
		this.token = token;
	}
	public String sessionId() {
		return this.sessionId;
	}
	public String token() {
		return this.token;
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.sessionId).append(this.token).toHashCode(); 
	}
}
