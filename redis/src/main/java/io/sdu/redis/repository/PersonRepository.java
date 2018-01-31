package io.sdu.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.sdu.redis.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
