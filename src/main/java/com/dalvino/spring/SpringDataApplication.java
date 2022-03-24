package com.dalvino.spring;


import com.dalvino.spring.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@ComponentScan(basePackages = {"com.dalvino.spring.orm","com.dalvino.spring.service"})
@EnableJpaRepositories("com.dalvino.spring.repository")
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudUnidadeService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	private Boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService,
								 CrudUnidadeService unidadeService,
								 CrudFuncionarioService funcionarioService,
								 RelatorioService relatorioService,
								 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico){

		this.cargoService  = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (system){
			System.out.println("Qual ação deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionarios");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio Dinâmico");

			int action = scanner.nextInt();
			switch (action){
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					unidadeService.inicial(scanner);
					break;
				case 3:
					funcionarioService.inicial(scanner);
					break;
				case 4:
					relatorioService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}

		}


	}
}
