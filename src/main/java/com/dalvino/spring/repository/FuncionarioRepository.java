package com.dalvino.spring.repository;


import com.dalvino.spring.orm.Funcionario;
import com.dalvino.spring.orm.FuncionarioProjecao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
    List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

    @Query(value = "SELECT * FROM funcionarios WHERE data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT id, nome, salario FROM funcionarios", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}

