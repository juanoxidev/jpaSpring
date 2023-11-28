package com.jpaSpring.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpaSpring.test.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
