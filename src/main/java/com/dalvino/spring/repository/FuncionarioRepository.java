package com.dalvino.spring.repository;


import com.dalvino.spring.orm.Funcionario;
import com.dalvino.spring.orm.Unidade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
    List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios WHERE data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> fundDataContratacaoMaior(LocalDate data);
}
