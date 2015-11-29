package biz.allrounder.jee7sample.domain.model;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentRepository {

	Collection<Department> find();
	Optional<Department> get(Long projectId);
	void save(Department project);
}
