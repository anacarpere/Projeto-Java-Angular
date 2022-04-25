package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.FuncionarioRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {
	
	//injeção de dependencia - chamando o repository de servicos
	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository; 
	
	
	//findAll dos servicos cadastrados
	
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	//findById - busca o serviço pelo ID 
	
	public Servico mostrarUmServico(Integer idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		return servico.orElseThrow();
	}
	
	public List<Servico> buscarServicosDoFuncionario(Integer idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return servicoRepository.findByFuncionario(funcionario);
	}
	
	public List<Servico>buscarServicosPelaData(Date dataEntrada){
		return servicoRepository.findByDataEntrada(dataEntrada);
	}
	
	public List<Servico>buscarServicosEntreDatas(Date data1, Date data2){
		return servicoRepository.findByIntervaloData(data1, data2);
	}
	
	public List<Servico>buscarServicosPeloStatus(String status){
		return servicoRepository.findByStatus(status);
	}
	
	public List<Servico> buscarServicoSemFuncionario(){
		return servicoRepository.findByIdFuncionarioIsNull();
	}
	
	//método para cadastro de um serviço 
	//no momento do cadastro do serviços o status tem que ser recebido
	//id_funcionario tem que ser null
	public Servico inserirServico(Servico servico) {
		servico.setIdServico(null);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setFuncionario(null);
		return servicoRepository.save(servico);
		
	}
	
	//método para atribuir um servico para um funcionario 
	
	public Servico atribuirFuncionario(Integer IdServico, Integer IdFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(IdFuncionario);
		Servico servico = mostrarUmServico(IdServico);
		servico.setIdServico(IdServico);
		servico.setFuncionario(funcionario.get());
		servico.setStatus(StatusServico.ATRIBUIDO);
		
		return servicoRepository.save(servico);
	}
	
	//Método para mudar o status do serviço para concluido
	public Servico concluirServico(Integer idServico) {
		Servico servico = mostrarUmServico(idServico);
		servico.setIdServico(idServico);
		if (servico.getFuncionario() != null) {
			servico.setStatus(StatusServico.CONCLUIDO);		
		}
		return servicoRepository.save(servico);	
				
	}
	
	//deleteById - excluir um serviço pela sua chave primária
	public void deletarUmServico(Integer idServico) {
		servicoRepository.deleteById(idServico);		
	}
	
	//editar dados de um serviço
	public Servico editarServico(Servico servico, Integer idFuncionario) {
		mostrarUmServico(servico.getIdServico());
		Funcionario funcionario = funcionarioRepository.getById(idFuncionario);
		servico.setFuncionario(funcionario);
		return servicoRepository.save(servico);
	}
	

}
