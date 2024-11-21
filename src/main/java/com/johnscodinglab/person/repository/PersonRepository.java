package com.johnscodinglab.person.repository;

import com.johnscodinglab.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByEmail(String email);
}
