package com.jpaSpring.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpaSpring.test.model.Mascota;
@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Long> {

}
