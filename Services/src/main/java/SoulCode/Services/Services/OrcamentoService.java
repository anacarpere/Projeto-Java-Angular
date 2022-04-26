package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusOrcamento;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.OrcamentoRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class OrcamentoService {
	
	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	public List<Orcamento> mostrarTodosOrcamentos(){
		return orcamentoRepository.findAll();		
	}
	
	public Orcamento mostrarUmOrcamento(Integer IdOrcamento) {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(IdOrcamento);
		return orcamento.orElseThrow();
	}
	
	//Orcamento por status
	public List<Orcamento>mostrarOrcamentosPeloStatus(String status){
		return orcamentoRepository.findByStatus(status);
	}
	
	public Orcamento inserirOrcamento(Orcamento orcamento, Integer idServico) {
		orcamento.setIdOrcamento(idServico);
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		orcamentoRepository.save(orcamento);
		Servico servico = servicoRepository.getById(idServico);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
				
	}
	
	//servico para pagamento de um orçamento (liquidar um orçamento
	//modificar o status para quitado
	
	public Orcamento quitarOrcamento(Integer idOrcamento) {
		Orcamento orcamento = mostrarUmOrcamento(idOrcamento);
		orcamento.setStatus(StatusOrcamento.QUITADO);
		return orcamentoRepository.save(orcamento);
		
	}
	
	//deletar orçamento


	public void excluirOrcamento(Integer idOrcamento) {
		Servico servico = servicoRepository.getById(idOrcamento);
		servico.setOrcamento(null);
		servico.setStatus(StatusServico.ARQUIVADO);
		servicoRepository.save(servico);
		orcamentoRepository.deleteById(idOrcamento);
		
	}
	
	public Orcamento editarOrcamento(Orcamento orcamento, Integer idOrcamento) {
		mostrarUmOrcamento(orcamento.getIdOrcamento());
		return orcamentoRepository.save(orcamento);
	}

}



















