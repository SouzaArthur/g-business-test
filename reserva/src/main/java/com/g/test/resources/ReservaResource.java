package com.g.test.resources;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g.test.dto.NewReserveDTO;
import com.g.test.services.ReservaService;

@RestController
@RequestMapping(value="/reserva")
public class ReservaResource {
	
	@Autowired
	ReservaService reservaService;

	@PostMapping(value="/reservar")
	public String reserveNow(@RequestBody NewReserveDTO reserveObj) throws Exception {
		
		String reserveReturn = reservaService.reserveNow(reserveObj);
		
		return reserveReturn;
	} 
}
