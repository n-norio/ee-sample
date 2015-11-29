package biz.allrounder.jee7sample.resources;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import biz.allrounder.jee7sample.domain.model.Department;
import biz.allrounder.jee7sample.domain.model.Person;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DepartmentJsonView {

	@JsonCreator
	public DepartmentJsonView(
			@JsonProperty("name") String name,
			@JsonProperty("persons") Collection<PersonJsonView> persons) {
		
		this.name = name;
		this.persons = persons;
	}
	
	public DepartmentJsonView(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DepartmentJsonView(
			Long id,
			String name,
			Collection<PersonJsonView> persons) {
		this(id, name);
		this.persons = persons;
	}
	
	public Department buildProject() {
		Department project = new Department(this.name);

		for (PersonJsonView projectItemView: this.persons) {
			Person person = projectItemView.buildPerson();
			person.department(project);
			project.add(person);
		}
		return project;
	}
	
	public static DepartmentJsonView valueOf(Department department) {
		Collection<PersonJsonView> persons = new ArrayList<>();
		for (Person person: department.persons()) {
			persons.add(new PersonJsonView(
					person.id(), person.name(), person.position()));
		}
		return new DepartmentJsonView(department.id(), department.name(), persons);
	}

	@JsonProperty
	private Long id;
	
	@JsonProperty
	@NotEmpty
	private String name;
	
	@JsonProperty
	@Valid
	private Collection<PersonJsonView> persons;
}
