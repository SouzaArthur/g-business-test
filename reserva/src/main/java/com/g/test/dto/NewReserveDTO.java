package com.g.test.dto;

import java.io.Serializable;

public class NewReserveDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String idPrograma;
	
	private String dataExibicao;
	
	private Integer quantidade;
	
	private Integer tempo;
	
	public NewReserveDTO(String idPrograma, String dataExibicao, Integer quantidade, Integer tempo) {
		super();
		this.idPrograma = idPrograma;
		this.dataExibicao = dataExibicao;
		this.quantidade = quantidade;
		this.tempo = tempo;
	}
	
	public NewReserveDTO() {}

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getDataExibicao() {
		return dataExibicao;
	}

	public void setDataExibicao(String dataExibicao) {
		this.dataExibicao = dataExibicao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	
}
