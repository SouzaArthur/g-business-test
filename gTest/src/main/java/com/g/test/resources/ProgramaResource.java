package com.g.test.resources;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.g.test.domain.Programa;
import com.g.test.dto.ProgramaDTO;
import com.g.test.services.ProgramaService;

@RestController
@RequestMapping(value="/programa")
public class ProgramaResource {
	
	@Autowired
	ProgramaService service;
	
	//Get method
	
	@GetMapping(value="/{idString}")
	public ResponseEntity<?> find(@PathVariable String idString) {
		Programa obj = service.find(idString);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/delete/{idString}")
	public ResponseEntity<Void> detele(@PathVariable String idString){
		service.deleteByIdString(idString);
		return ResponseEntity.noContent().build();
	} 
	
	@PostMapping(value="/save")
	public ResponseEntity<Void> insert(@Valid @RequestBody ProgramaDTO objDto){
		Programa obj = service.fromDto(objDto);
		
		//Setting creation date
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		long date = new Date().getTime();
		obj.setDataCadastro(date);
		
		//It is active
		obj.setAtivo(true);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping(value="/inverted/{idString}")
	public ResponseEntity<?> findNameInverted(@PathVariable String idString) {
		String obj = service.findNameInverted(idString);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/{idString}")
	public ResponseEntity<Void> updateProgram(@Valid @RequestBody ProgramaDTO objDto, @PathVariable String idString){
		Programa obj = service.fromDto(objDto);
		
		//Setando a id do objeto recebido no body, para que seja o mesmo recebido na URL
		obj.setId(idString);
		
		service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
}
