package com.g.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g.test.domain.Programa;
import com.g.test.dto.ProgramaDTO;
import com.g.test.repositories.ProgramaRepository;

@Service
public class ProgramaService {

	@Autowired
	private ProgramaRepository repository;
	
	public Programa find(String stringId) {
		return repository.findIdString(stringId);
	}
	
	public void deleteByIdString(String idString) {
		repository.deleteByIdString(idString);
	}
	
	public Programa fromDto(ProgramaDTO objDto) {
		return new Programa(objDto.getId(), objDto.getNome(), 0, false);
	}
	
	public Programa insert(Programa obj) {
		return repository.save(obj);
	}
	
	public String findNameInverted(String stringId) {
		//Inverting name string
		 
		Programa programObj = repository.findIdString(stringId);
		String programName = programObj.getNome();
		String newProgramName = "";

		newProgramName = this.invertName(programName);
		
		return newProgramName;
	}
	
	public Programa update(Programa obj) {
		return repository.save(obj);
	}
	
	public String invertName(String programName) {
		String newProgramName = "";
		
		for(int i = programName.length() - 1; i >= 0; i--) {
			newProgramName = newProgramName + programName.charAt(i);
		}
		
		return newProgramName;
	}
}