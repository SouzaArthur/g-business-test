package com.g.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.g.test.domain.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Estoque obj WHERE obj.idPrograma = :stringId")
	public List<Estoque> findByIdProgramString(@Param("stringId") String stringId);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Estoque obj WHERE obj.idPrograma = :stringId AND obj.tempoDisponivel >= :time AND obj.tempoDisponivel <= obj.tempoDisponivel")
	public List<Estoque> findSolicitedTime(@Param("stringId") String stringId, @Param("time") Integer time);
	
}
