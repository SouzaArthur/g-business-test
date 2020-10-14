package com.g.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.g.test.domain.Estoque;
import com.g.test.repositories.EstoqueRepository;

@SpringBootApplication
public class ReservaApplication implements CommandLineRunner{
	
	@Autowired
	EstoqueRepository estoqueRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReservaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Estoque es1 = new Estoque("MAVO", df.parse("04/09/2020"), 100);
		Estoque es2 = new Estoque("N19H", df.parse("09/09/2020"), 100);
		Estoque es3 = new Estoque("HUCK", df.parse("05/09/2020"), 40);
		Estoque es4 = new Estoque("DFAU", df.parse("06/09/2020"), 100);	
		
		estoqueRepository.saveAll(Arrays.asList(es1, es2, es3, es4));
		
	}

}
