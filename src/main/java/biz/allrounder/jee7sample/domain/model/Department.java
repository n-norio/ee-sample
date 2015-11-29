package biz.allrounder.jee7sample.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
@NamedQueries({
    @NamedQuery(name="Department.find",
                query="SELECT e FROM Department e"),
}) 
public class Department {

	public Department() {}
	
	public Department(String name) {
		this.name = name;
	}
	
	@Id
	@Column(name="ID")
	@GeneratedValue(generator="DEPARTMENT_SEQUENCE", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="DEPARTMENT_SEQUENCE", sequenceName="DEPARTMENT_SEQUENCE")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<Person> persons = new ArrayList<>();
	
	@Column(name="CREATED_AT")
	private String createdAt;
	@Column(name="CREATED_DTIME")
	private Date createdDtime;
	@Column(name="UPDATED_AT")
	private String updatedAt;
	@Column(name="UPDATED_DTIME")
	private Date updatedDtime;
	
	public void merge(Department updatedDepartment) {
		this.name = updatedDepartment.name();
	}
	
	@PrePersist
	public void preSave() {
		this.createdDtime = new Date();
		this.updatedDtime = this.createdDtime;
	}
	@PreUpdate
	public void preUpdate() {
		this.updatedDtime = new Date();
	}
	
	public void add(Person person) {
		persons.add(person);
	}
	
	public Long id() {
		return id;
	}
	public String name() {
		return name;
	}
	public Collection<Person> persons() {
		return this.persons;
	}
}
