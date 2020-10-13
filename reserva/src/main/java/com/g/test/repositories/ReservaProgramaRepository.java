package com.g.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g.test.domain.ReservaPrograma;

@Repository
public interface ReservaProgramaRepository extends JpaRepository<ReservaPrograma, Integer>{

}
