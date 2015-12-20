package biz.allrounder.jee7sample.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person {

	public Person() {}
	
	public Person(Long id, String name, String position, int salary) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="PERSON_SEQUENCE", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="PERSON_SEQUENCE", sequenceName="PERSON_SEQUENCE")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="POSITION")
	private String position;
	
	@Column(name="SALARY")
	private int salary;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	private Department department;
	
	public void merge(Person updatedPerson) {
		this.name = updatedPerson.name();
		this.position = updatedPerson.position();
		this.salary = updatedPerson.salary();
	}
	
	public Long id() {
		return this.id;
	}
	public String position() {
		return this.position;
	}
	public String name() {
		return this.name;
	}
	public int salary() {
		return this.salary;
	}
	public void department(Department department) {
		this.department = department;
	}
}
