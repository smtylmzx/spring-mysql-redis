package com.sametyilmaz.app.repo;

import com.sametyilmaz.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
