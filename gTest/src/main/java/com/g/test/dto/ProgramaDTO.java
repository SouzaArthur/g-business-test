package com.g.test.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.g.test.domain.Programa;

public class ProgramaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Id is required")
	@Length(min=1, max=10, message="The id should have between 1 and 10 characters")
	private String id;
	
	@NotEmpty(message="Nome is required")
	@Length(min=1, max=100, message="The nome field should have between 1 and 100 characters")
	private String nome;
	
	public ProgramaDTO(Programa obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public ProgramaDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
