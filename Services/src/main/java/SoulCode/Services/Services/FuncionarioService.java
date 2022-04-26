package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepository;


@Service
public class FuncionarioService {
	
	//aqui fazemos 
	//injeção de dependência
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//Serviço para   buscar todos os funcionários cadastrados
	public List<Funcionario> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll();
		
	}
	 //findById - busca funcionario pelo seu Id
	public Funcionario mostrarUmFuncionario(Integer idFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return funcionario.orElseThrow();
	}
	
	//findByEmail
	public Funcionario mostrarFuncionarioPeloEmail(String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
		return funcionario.orElseThrow();
	}
	
	//findByNomeAndEmail
	public Funcionario mostrarFuncionarioPeloNomeEEmail(String nome, String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByNomeAndEmail(nome, email);
		return funcionario.orElseThrow();
	}
		
	
	
	//save - insere um novo registro na tabela do nosso db 
	
	public Funcionario inserirFuncionario(Funcionario funcionario) {
		//por precaução vamos limpar o campo de id do funcionário 
		funcionario.setIdFuncionario(null);
		return funcionarioRepository.save(funcionario);
		
		//o método save pega os dados do novo funcionario, salva no db e já gera um id para esse 
		
	}
	
	//editar um funcionário já cadastrado
	public Funcionario editarFuncionario (Funcionario funcionario) {
		mostrarUmFuncionario(funcionario.getIdFuncionario());
		return funcionarioRepository.save(funcionario);
	}
	
	//deleteById - excluir um funcionário pelo seu Id
	public void excluirFuncionario (Integer idFuncionario) {
		mostrarUmFuncionario(idFuncionario);
		funcionarioRepository.deleteById(idFuncionario);
	}
	
	public Funcionario salvarFoto(Integer idFuncionario, String caminhoFoto) {
		Funcionario funcionario = mostrarUmFuncionario(idFuncionario);
		funcionario.setFoto(caminhoFoto);
		return funcionarioRepository.save(funcionario);
	}
	

	
}
