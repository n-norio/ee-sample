package biz.allrounder.jee7sample.resources;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import biz.allrounder.jee7sample.domain.model.Person;
import biz.allrounder.jee7sample.resources.constrains.SalaryConstraint;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SalaryConstraint
public class PersonJsonView {

	@JsonCreator
	public PersonJsonView(
			@JsonProperty("name") String name,
			@JsonProperty("position") String position,
			@JsonProperty("salary") int salary) {
		
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public PersonJsonView(Long id, String name, String position, int salary) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public Person buildPerson() {
		return new Person(this.id, this.name, this.position, this.salary);
	}
	
	@JsonProperty
	private Long id;
	@JsonProperty
	@NotEmpty
	@Size(max=5)
	private String name;
	@JsonProperty
	private String position;
	@JsonProperty
	private int salary;
	
	public String position() { return this.position; }
	public int salary() { return this.salary; }
	
}
