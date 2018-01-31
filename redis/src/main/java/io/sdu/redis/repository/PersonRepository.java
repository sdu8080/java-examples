package io.polarisdev.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.polarisdev.redis.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
