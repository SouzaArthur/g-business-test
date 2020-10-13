package com.g.test.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g.test.domain.Reserva;
import com.g.test.domain.ReservaPrograma;
import com.g.test.dto.NewReserveDTO;
import com.g.test.repositories.ReservaProgramaRepository;
import com.g.test.repositories.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	ReservaProgramaRepository reservaProgramaRepository;
	
	public String reserveNow(NewReserveDTO reserveObj) throws ParseException {
		
		//Generating codigoReserva
		String codigoReserva = UUID.randomUUID().toString();
		
		//Generating dataReserva
		Date reserveDate = new Date();
		
		//Generating requestID
		UUID requestID = UUID.randomUUID();
		
		//Creating reservaObj to populate table
		Reserva reservaToSaveObj = new Reserva(codigoReserva,  reserveDate, requestID);
		
		//Saving Reserva to get it's id
		Reserva reservaReturned = reservaRepository.save(reservaToSaveObj);
		
		//Getting it's id
		Integer reservaId = reservaReturned.getId();
		
		//Converting dataExibição
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dataExibicao = df.parse(reserveObj.getDataExibicao());
		
		
		//Creating reservaProgramaObj to populate table
		ReservaPrograma reservaProgramaToSaveObj = new ReservaPrograma(reserveObj.getIdPrograma(), reservaId, reserveObj.getQuantidade(), reserveObj.getTempo(), dataExibicao);
		
		//Saving ReservaPrograma
		reservaProgramaRepository.save(reservaProgramaToSaveObj);
		
		//Returning codigoReserva
	
		return codigoReserva;
	}
}
