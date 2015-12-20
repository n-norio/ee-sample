package biz.allrounder.jee7sample.application;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

	@Resource(name = "java:jboss/mail/Default")
	private Session session;

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
		for (Person updatedPerson : updatedDepartment.persons()) {
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
		try {
			System.out.println("send mail.");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("x@x"));
			Address toAddress = new InternetAddress("x@x");
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject("test");
			message.setText("aiueo");
			Transport.send(message);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@ExcludeDefaultInterceptors
	public void sendMailWithNewTx() {
		System.out.println("sent mail with new tx.");
	}
}
