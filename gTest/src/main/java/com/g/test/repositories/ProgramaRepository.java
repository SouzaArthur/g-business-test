package com.g.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.g.test.domain.Programa;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Programa obj WHERE obj.id = :stringId")
	public Programa findIdString(@Param("stringId") String stringId);
	
	@Transactional(readOnly=false)
	@Query("UPDATE Programa obj SET obj.ativo = false where obj.id = :idString")
	@Modifying
	public void deleteByIdString(@Param("idString") String idString);
}
