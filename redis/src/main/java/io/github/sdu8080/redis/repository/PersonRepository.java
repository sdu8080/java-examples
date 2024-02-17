package io.github.sdu8080.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.sdu8080.redis.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
