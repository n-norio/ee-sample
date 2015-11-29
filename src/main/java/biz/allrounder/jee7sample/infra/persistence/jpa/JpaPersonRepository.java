package biz.allrounder.jee7sample.infra.persistence.jpa;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import biz.allrounder.jee7sample.domain.model.Person;
import biz.allrounder.jee7sample.domain.model.PersonRepository;

@ApplicationScoped
public class JpaPersonRepository implements PersonRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Person> get(Long id) {
		return Optional.ofNullable(em.find(Person.class, id));
	}

	@Override
	public void persist(Person person) {
		em.persist(person);
	}
	
	
}
