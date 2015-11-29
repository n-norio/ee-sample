package biz.allrounder.jee7sample.application;

import java.util.Collection;
import java.util.Optional;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.NoResultException;

import biz.allrounder.jee7sample.domain.model.Department;
import biz.allrounder.jee7sample.domain.model.DepartmentRepository;
import biz.allrounder.jee7sample.domain.model.Person;
import biz.allrounder.jee7sample.domain.model.PersonRepository;
import biz.allrounder.jee7sample.domain.model.PersonWasRegisted;
import biz.allrounder.jee7sample.exception.ProjectApplicationException;

@Singleton
public class DepartmentService {

	@Inject
	private DepartmentRepository departmentRepository;
	@Inject 
	private PersonRepository personRepository;
	@Inject
	private Event<PersonWasRegisted> events;
	
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
		events.fire(new PersonWasRegisted());
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
	
	@Asynchronous
	public void sendMail() {
		System.out.println("send mail.");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@ExcludeDefaultInterceptors
	public void sendMailWithNewTx() {
		System.out.println("sent mail with new tx.");
	}
}
