package SoulCode.Services.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SoulCode.Services.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

}
