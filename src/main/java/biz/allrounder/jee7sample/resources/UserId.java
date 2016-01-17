package biz.allrounder.jee7sample.resources;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserId {
	
	private String value;
	
	public void set(String userId) { this.value = userId; }
	public String get() { return this.value; }
}
