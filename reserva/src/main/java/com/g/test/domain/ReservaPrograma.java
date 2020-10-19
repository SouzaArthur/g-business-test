package com.g.test.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReservaPrograma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String idPrograma;
		
	private Integer quantidade;
	
	private Integer tempo;
	
	@JsonFormat(pattern="dd/MM/yyyy", timezone="America/Sao_Paulo")
	private Date dataExibicao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_reserva")
	private Reserva reserva;

	public ReservaPrograma(String idPrograma, Integer quantidade, Integer tempo,
			Date dataExibicao) {
		super();
		this.idPrograma = idPrograma;
		this.quantidade = quantidade;
		this.tempo = tempo;
		this.dataExibicao = dataExibicao;
	}
	
	public ReservaPrograma() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
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

	public Date getDataExibicao() {
		return dataExibicao;
	}

	public void setDataExibicao(Date dataExibicao) {
		this.dataExibicao = dataExibicao;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
		ReservaPrograma other = (ReservaPrograma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
