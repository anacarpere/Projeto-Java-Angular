package SoulCode.Services.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer idCliente; 
	
	@Column
	private String nomeCliente;
	
	@Column
	private String emailCliente;
	
	@Column
	private String cidadeCliente; 


}
