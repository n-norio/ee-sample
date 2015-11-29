package biz.allrounder.jee7sample.resources;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import biz.allrounder.jee7sample.domain.model.Person;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonJsonView {

	@JsonCreator
	public PersonJsonView(
			@JsonProperty("name") String name,
			@JsonProperty("position") String position) {
		this.name = name;
		this.position = position;
	}
	
	public PersonJsonView(Long id, String name, String position) {
		this.id = id;
		this.name = name;
		this.position = position;
	}
	
	public Person buildPerson() {
		return new Person(this.id, this.name, this.position);
	}
	
	@JsonProperty
	private Long id;
	@JsonProperty
	@NotEmpty
	private String name;
	@JsonProperty
	private String position;

}
