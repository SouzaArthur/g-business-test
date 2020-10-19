package com.g.test.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g.test.domain.ReservaPrograma;
import com.g.test.services.ReservaService;

@RestController
@RequestMapping(value="/reserva")
public class ReservaResource {
	
	@Autowired
	ReservaService reservaService;

	@PostMapping(value="/reservar")
	public String reserveNow(@RequestBody List<ReservaPrograma> reserveObj) throws Exception {
		
		String reserveReturn = reservaService.reserveNow(reserveObj);
		
		return reserveReturn;
	} 
}
