package biz.allrounder.jee7sample.infra.persistence.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import biz.allrounder.jee7sample.domain.model.Department;
import biz.allrounder.jee7sample.domain.model.DepartmentRepository;

@ApplicationScoped
public class JpaDepartmentRepository implements DepartmentRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Department> find() {
		return em.createNamedQuery("Department.find", Department.class).getResultList();
	}

	@Override
	public Optional<Department> get(Long projectId) {
		return Optional.ofNullable(em.find(Department.class, projectId));
	}

	@Override
	public void save(Department project) {
		em.persist(project);
	}

}
