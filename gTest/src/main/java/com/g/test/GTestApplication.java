package com.g.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.g.test.domain.Programa;
import com.g.test.repositories.ProgramaRepository;

@SpringBootApplication
public class GTestApplication implements CommandLineRunner{
	
	@Autowired
	private ProgramaRepository programaRepository;

	public static void main(String[] args) {
		SpringApplication.run(GTestApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String defaultDate = "31/09/2020 00:00:00";
		Timestamp timeStamp = new Timestamp(dt.parse(defaultDate).getTime());
		
		Programa pro1 = new Programa("MAVO", "MAIS VOCE", timeStamp.getTime(), true);
		Programa pro2 = new Programa("N19H", "NOVELA 1", timeStamp.getTime(), true);
		Programa pro3 = new Programa("HUCK", "CALDEIRAO DO HUCK", timeStamp.getTime(), true);
		Programa pro4 = new Programa("DFAU", "DOMINGAO DO FAUSTAO", timeStamp.getTime(), true);
		
		programaRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4));
	}

}
