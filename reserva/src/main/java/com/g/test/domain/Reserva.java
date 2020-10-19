package com.g.test.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String codigoReserva;
	
	@JsonFormat(pattern="dd/MM/yyyy", timezone="America/Sao_Paulo")
	private Date dataReserva;
	
	private UUID requestId;
	
	@OneToMany(mappedBy="reserva", cascade=CascadeType.ALL)
	private List<ReservaPrograma> reservaProgramaList; 
	
	public Reserva(String codigoReserva, Date dataReserva, UUID requestId) {
		super();
		this.codigoReserva = codigoReserva;
		this.dataReserva = dataReserva;
		this.requestId = requestId;
	}

	public Reserva() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public UUID getRequestId() {
		return requestId;
	}

	public void setRequestId(UUID requestId) {
		this.requestId = requestId;
	}

	public List<ReservaPrograma> getReservaProgramaList() {
		return reservaProgramaList;
	}

	public void setReservaProgramaList(List<ReservaPrograma> reservaProgramaList) {
		this.reservaProgramaList = reservaProgramaList;
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
		Reserva other = (Reserva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
