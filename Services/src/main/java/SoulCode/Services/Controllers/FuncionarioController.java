package SoulCode.Services.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Services.FuncionarioService;

@CrossOrigin //evitar erro de permissão de acesso das portas do front e back
@RestController // para dizer que é uma classe de controller
@RequestMapping("servicos")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/funcionario")
	public List<Funcionario>mostrarTodosFuncionarios(){
		List<Funcionario> funcionarios = funcionarioService.mostrarTodosFuncionarios();
		return funcionarios; 
	}

}
