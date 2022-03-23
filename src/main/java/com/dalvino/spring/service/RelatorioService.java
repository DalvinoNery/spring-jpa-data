package com.dalvino.spring.service;

import com.dalvino.spring.orm.Funcionario;
import com.dalvino.spring.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private FuncionarioRepository funcionarioRepository;
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Pesquisar funcionario por nome");
            System.out.println("2 - Pesquisar funcionario por nome, data de contratação e maior salario");
            System.out.println("3 - Pesquisar funcionario por data de contratação");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    pesquisarFuncionarioPorNome(scanner);
                    break;
                case 2:
                    pesquisarFuncionarioNomeDataMaiorSalario(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }

    private void pesquisarFuncionarioPorNome(Scanner scanner) {

        System.out.println("Informe um nome de funcionário para pesquisa:");
        String nome = scanner.next();

        List<Funcionario> funcionarioList = funcionarioRepository.findByNome(nome);

        funcionarioList.forEach(System.out::println);
    }

    private void pesquisarFuncionarioNomeDataMaiorSalario(Scanner scanner) {

        System.out.println("Informe um nome de funcionário para pesquisa:");
        String nome = scanner.next();

        System.out.println("Informe a data da contratação:");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Informe o valor do salario:");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarioList = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario, localDate);

        funcionarioList.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Informe a data da contratação:");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarioList = funcionarioRepository.fundDataContratacaoMaior(localDate);

        funcionarioList.forEach(System.out::println);

    }
}
