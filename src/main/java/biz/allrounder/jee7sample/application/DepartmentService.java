package biz.allrounder.jee7sample.application;

import java.util.Collection;
import java.util.Optional;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import biz.allrounder.jee7sample.domain.model.Department;
import biz.allrounder.jee7sample.domain.model.DepartmentRepository;
import biz.allrounder.jee7sample.domain.model.Person;
import biz.allrounder.jee7sample.domain.model.PersonRepository;
import biz.allrounder.jee7sample.exception.ProjectApplicationException;

@Singleton
public class DepartmentService {

	@Inject
	private DepartmentRepository departmentRepository;
	@Inject 
	private PersonRepository personRepository;
	
	public Collection<Department> find() {
		return departmentRepository.find();
	}
	
	public Department get(Long departmentId) {
		Department department = departmentRepository.get(departmentId)
				.orElseThrow(() -> new ProjectApplicationException(new NoResultException()));
		department.persons().size();
		return department;
	}
	
	public void persist(Department department) {
		departmentRepository.save(department);
	}
	
	public void update(Long deptId, Department updatedDepartment) {
		Department department = departmentRepository.get(deptId)
				.orElseThrow(() -> new ProjectApplicationException(new NoResultException()));
		department.merge(updatedDepartment);
		for (Person updatedPerson: updatedDepartment.persons()) {
			if (updatedPerson.id() == null) {
				updatedPerson.department(department);
				personRepository.persist(updatedPerson);
				
			} else {
				Optional<Person> personOpt = personRepository.get(updatedPerson.id());
				personOpt.ifPresent(person -> person.merge(updatedPerson));	
			}
		}
	}
}
