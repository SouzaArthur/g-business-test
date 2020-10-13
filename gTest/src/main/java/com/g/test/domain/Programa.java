package com.g.test.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Programa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String nome;
	
	private long dataCadastro;
	
	private boolean ativo;
	
	private long dataExclusao;
	
	public Programa() {
		
	}
	
	public Programa(String id, String nome, long dataCadastro, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
	}

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

	public long getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(long dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public long getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(long dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Programa other = (Programa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
