package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Servico;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {
	
	//injeção de dependencia - chamando o repository de servicos
	@Autowired
	ServicoRepository servicoRepository;
	
	//findAll dos servicos cadastrados
	
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	//findById - busca o serviço pelo ID 
	
	public Servico mostrarUmServico(Integer idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		return servico.orElseThrow();
	}
	

}
