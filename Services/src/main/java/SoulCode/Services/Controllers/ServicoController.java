package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Servico;
import SoulCode.Services.Services.ServicoService;

@CrossOrigin
@RestController
@RequestMapping("servicos")

public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	@GetMapping("/servico")
	public List<Servico>mostrarTodosServicos(){
		List<Servico> servicos = servicoService.mostrarTodosServicos();
		return servicos;
	};
	
	@GetMapping("/servico/{idServico}")
	public ResponseEntity<Servico> mostrarUmServico(@PathVariable Integer idServico){
		Servico servico = servicoService.mostrarUmServico(idServico);
		return ResponseEntity.ok().body(servico);
	}
	
	@GetMapping("/servico/funcionario/{idFuncionario}")
	public List<Servico>buscarServicosDoFuncionario(@PathVariable Integer idFuncionario){
		List<Servico> servicos = servicoService.buscarServicosDoFuncionario(idFuncionario);
		return servicos;
	}
	@GetMapping("/servicoData")
	public List<Servico>buscarServicosPelaData(@RequestParam("dataEntrada") @DateTimeFormat(
			iso = DateTimeFormat.ISO.DATE) Date dataEntrada){
		List<Servico> servicos = servicoService.buscarServicosPelaData(dataEntrada);
		return servicos;
	}
	
	@GetMapping("/servicoIntervaloData")
	public List<Servico>buscarServicosEntreDatas(@RequestParam("data1") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1, 
			@RequestParam("data2") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2){
		List<Servico> servicos = servicoService.buscarServicosEntreDatas(data1, data2);
		return servicos;
	}
	
	@GetMapping("/servicoStatus")
	public List<Servico>buscarServicosPeloStatus(@RequestParam("status")String status){
		List<Servico> servicos = servicoService.buscarServicosPeloStatus(status);
		return servicos;
		
	}
	
	@GetMapping("/servicoNaoAtribuido")
	public List<Servico>buscarServicoSemFuncionario(){
		List<Servico> servicos = servicoService.buscarServicoSemFuncionario();
		return servicos;
	}
	
	@PostMapping("/servico")
	public ResponseEntity<Servico>inserirServico(@RequestBody Servico servico){
		servico = servicoService.inserirServico(servico);
		URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servico.getIdServico()).toUri();
		return ResponseEntity.created(novaUri).build();
		
	}
	
	@PostMapping("/atribuirServico/{idServico}/{idFuncionario}")
	public ResponseEntity<Servico>atribuirFuncionario(@PathVariable Integer idServico, @PathVariable Integer idFuncionario){
		 servicoService.atribuirFuncionario(idServico, idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/concluirServico/{idServico}")
	public ResponseEntity<Servico> concluirServico(@PathVariable Integer idServico){
		servicoService.concluirServico(idServico);
		return ResponseEntity.noContent().build(); 
	}
	
	@DeleteMapping("/servico/{idServico}")
	public ResponseEntity<Void> deletarUmServico(@PathVariable Integer idServico){
		servicoService.deletarUmServico(idServico);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/servico/{idServico}/{idFuncionario}")
	public ResponseEntity<Void>editarFuncionario(@PathVariable Integer idServico, @PathVariable Integer idFuncionario, @RequestBody Servico servico){
		servico.setIdServico(idServico);
		servico = servicoService.editarServico(servico, idFuncionario);
		return ResponseEntity.noContent().build();
	}
	

}
