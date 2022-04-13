package SoulCode.Services.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	//injeção de dependência
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//Serviço para   buscar todos os funcionários cadastrados
	public List<Funcionario> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll();
		
	}
}
