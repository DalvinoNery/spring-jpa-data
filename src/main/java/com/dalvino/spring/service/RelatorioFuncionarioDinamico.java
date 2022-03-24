package com.dalvino.spring.service;

import com.dalvino.spring.orm.Funcionario;
import com.dalvino.spring.repository.FuncionarioRepository;
import com.dalvino.spring.spacification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {
    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository =funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        System.out.println("Digite o nome do funcionario");
        String nome = scanner.next();

        if(nome.equalsIgnoreCase("NULL")){
            nome = null;
        }
        System.out.println("Digite o cpf do funcionario");
        String cpf = scanner.next();

        if(cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite o salario do funcionario");
        Double salario = scanner.nextDouble();

        if(salario== 0){
            salario = null;
        }

        System.out.println("Digite o data de contratação do funcionario");
        String data = scanner.next();

        LocalDate  dataContratacao;

        if(data.equalsIgnoreCase("NULL")){
            dataContratacao = null;
        }else{
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarioList = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome)).
                        or(SpecificationFuncionario.cpf(cpf)).or(SpecificationFuncionario.salario(salario)).
                        or(SpecificationFuncionario.datacontratacao(dataContratacao)));

        funcionarioList.forEach(System.out::println);
    }
}
