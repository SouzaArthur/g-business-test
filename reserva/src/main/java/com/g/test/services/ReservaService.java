package com.g.test.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.g.test.domain.Estoque;
import com.g.test.domain.Reserva;
import com.g.test.domain.ReservaPrograma;
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
	
	public String reserveNow(List<ReservaPrograma> reserveObjList) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		//Gerando codigoReserva
		String codigoReserva = UUID.randomUUID().toString();
		
		//Gerando dataReserva
		Date reserveDate = new Date();
		
		//Gerando requestID
		UUID requestID = UUID.randomUUID();
		
		//Verificando possíveis erros
		for(int i = 0; i < reserveObjList.size(); i++) {
			//Verificando se os objetos recebidos existem na tabela programa

			//URL para API para consultar programa 
			String uri = "http://localhost:8081/programa/" + reserveObjList.get(i).getIdPrograma();
			
			String result = restTemplate.getForObject(uri, String.class);
			
			//Tratar Erro - Programa não encontrado
			if(result == null) {
				throw new Exception("Programa "+ reserveObjList.get(i).getIdPrograma()  + " não encontrado");
			}
			
			
			//Pegar todos os tempos disponíveis para esse programa
			List<Estoque> estoqueObjReturned = estoqueRepository.findByIdProgramString(reserveObjList.get(i).getIdPrograma());
			
			//Verificando se o tempo solicitado está disponível para o atual Programa
			List<Estoque> estoqueDisponivel = estoqueRepository.findSolicitedTime(reserveObjList.get(i).getIdPrograma(), reserveObjList.get(i).getTempo());
			
			if(estoqueDisponivel.isEmpty()) {
				throw new Exception("Não existe estoque disponível para o programa " + reserveObjList.get(i).getIdPrograma());
			}	
			
			// Tem estoque... agora verificar se tem estoque para a data solicitada
			
			// Verificando se a data solicitada está disponível para o programa atual
			for(int x = 0; x < estoqueDisponivel.size(); x++) {
				
				Date estoqueDataExibicao = new Date(estoqueDisponivel.get(x).getDataExibicao().getTime());
				
				//Pegando a data de exibição solicitada
				Date reserveObjDate = reserveObjList.get(i).getDataExibicao();
				
				//Tratar erro e verificar outras datas - Erro para data não disponível
				if(!reserveObjDate.equals(estoqueDataExibicao)) {
					
					//Só lançar o erro depois de verificar todo o estoque desse programa
					if(x + 1 == estoqueDisponivel.size()) {						
						throw new Exception("A data informada não está disponível no estoque para o programa " + reserveObjList.get(i).getIdPrograma());
					}
				}else {
					break;
				}
			}	

		}
		
		//Criando reservaObj para popular tabela
		Reserva reservaToSaveObj = new Reserva(codigoReserva,  reserveDate, requestID);
		
		//Salvando reserva
		Reserva reservaAfterSave = reservaRepository.save(reservaToSaveObj);
		
		//Salvando todos os programas reservados
		for(int y = 0; y < reserveObjList.size(); y++) {
			//Salvando reserva_programa
			ReservaPrograma reservaProgramaToSaveObj = new ReservaPrograma(reserveObjList.get(y).getIdPrograma(), reserveObjList.get(y).getQuantidade(), reserveObjList.get(y).getTempo(), reserveObjList.get(y).getDataExibicao());
			
			reservaProgramaToSaveObj.setReserva(reservaAfterSave);
			
			reservaProgramaRepository.save(reservaProgramaToSaveObj);
			
			//Verifinca de qual linha do estoque subtrair
			List<Estoque> estoqueDisponivel = estoqueRepository.findSolicitedTime(reserveObjList.get(y).getIdPrograma(), reserveObjList.get(y).getTempo());
			
			//Tempo real do programa atual sendo salvo
			Integer realTime = reserveObjList.get(y).getTempo() * reserveObjList.get(y).getQuantidade();
			
			for(int i = 0; i < estoqueDisponivel.size(); i++) {
				
				Date reserveObjDate = reserveObjList.get(y).getDataExibicao();
			
				if(reserveObjDate.equals(estoqueDisponivel.get(i).getDataExibicao())) {
					//Objeto estoque encontrado
					estoqueDisponivel.get(i).setTempoDisponivel(estoqueDisponivel.get(i).getTempoDisponivel() - realTime);
					
					estoqueRepository.save(estoqueDisponivel.get(i));
				}
			}
			
		}
		
		//Returning codigoReserva
	
		return "Código da Reserva: " + codigoReserva;
	}
	
}
