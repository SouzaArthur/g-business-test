package com.g.test.services.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.g.test.services.ProgramaService;

class ProgramaTest {

	@Test
	public void testInvertedName() {
		ProgramaService ps = new ProgramaService();
		
		String stringToInvert = "CALDEIRAO DO HUCK";
		
		assertEquals("KCUH OD OARIEDLAC", ps.invertName(stringToInvert));
		
	}

}
