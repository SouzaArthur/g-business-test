package com.g.test.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.g.test.domain.Estoque;
import com.g.test.domain.Reserva;
import com.g.test.domain.ReservaPrograma;
import com.g.test.dto.NewReserveDTO;
import com.g.test.repositories.EstoqueRepository;
import com.g.test.repositories.ReservaProgramaRepository;
import com.g.test.repositories.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
	EstoqueRepository estoqueRepository;
	
	@Autowired
	ReservaProgramaRepository reservaProgramaRepository;
	
	public String reserveNow(NewReserveDTO reserveObj) throws Exception {
		
		//Verifying if the program on reserveObj exists on program entity
		final String uri = "http://localhost:8081/programa/" + reserveObj.getIdPrograma();

	    RestTemplate restTemplate = new RestTemplate();
	    
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    if(result == null) {
	    	throw new Exception("Programa "+ reserveObj.getIdPrograma()  + " não encontrado");
	    }
		
		//Verifying if the solicited time is available
		//Getting available time for the given program
		Estoque estoqueObjReturned = estoqueRepository.findByIdProgramString(reserveObj.getIdPrograma());
		
		//Getting real time considering quantidade	
		Integer realTime = reserveObj.getTempo() * reserveObj.getQuantidade();
		
		if(estoqueObjReturned != null) {
			if(realTime > estoqueObjReturned.getTempoDisponivel()) {
				//Throw error
				throw new Exception("Não existe estoque disponível para o programa " + reserveObj.getIdPrograma());
			}
		}
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date estoqueDataExibicao = new Date(estoqueObjReturned.getDataExibicao().getTime());
		
		Date reserveObjDate = df.parse(reserveObj.getDataExibicao());
		
		//Verifying if the solicited date is available for program
		if(!reserveObjDate.equals(estoqueDataExibicao)) {
			throw new Exception("A data informada não está disponível no estoque para o programa " + reserveObj.getIdPrograma());
		}
		
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
		Date dataExibicao = df.parse(reserveObj.getDataExibicao());
		
		
		//Creating reservaProgramaObj to populate table
		ReservaPrograma reservaProgramaToSaveObj = new ReservaPrograma(reserveObj.getIdPrograma(), reservaId, reserveObj.getQuantidade(), reserveObj.getTempo(), dataExibicao);
		
		//Saving ReservaPrograma
		reservaProgramaRepository.save(reservaProgramaToSaveObj);
		
		//Removing the amount of time reserved from the Estoque entity
		estoqueObjReturned.setTempoDisponivel(estoqueObjReturned.getTempoDisponivel() - realTime);
		estoqueRepository.save(estoqueObjReturned);
		
		//Returning codigoReserva
	
		return "Código da Reserva: " + codigoReserva;
	}
}
