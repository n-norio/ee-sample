package biz.allrounder.jee7sample.domain.model;

import java.util.Optional;

public interface PersonRepository {

	Optional<Person> get(Long id);
	void persist(Person person);
}
