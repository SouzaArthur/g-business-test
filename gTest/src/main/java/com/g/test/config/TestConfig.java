package com.g.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.g.test.repositories.ProgramaRepository;

@Configuration
public class TestConfig {
	
	@Autowired
	private ProgramaRepository programaRepository;

}
