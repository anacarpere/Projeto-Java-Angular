package SoulCode.Services.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Funcionario;

@Repository
public interface FuncionarioRepository extends 
JpaRepository<Funcionario, Integer> {

}
