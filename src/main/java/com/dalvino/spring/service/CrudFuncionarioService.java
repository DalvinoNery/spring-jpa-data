package com.dalvino.spring.service;

import com.dalvino.spring.orm.Funcionario;
import com.dalvino.spring.orm.Unidade;
import com.dalvino.spring.repository.CargoRepository;
import com.dalvino.spring.repository.FuncionarioRepository;
import com.dalvino.spring.repository.UnidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeRepository unidadeRepository;

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  UnidadeRepository unidadeRepository){

        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeRepository = unidadeRepository;
    }

    public void inicial(Scanner scanner){
        while (system) {
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salva");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Excluir");
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
               //     atualizar(scanner);
                    break;
                case 3:
                    listar(scanner);
                    break;
                case 4:
              //      deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void salvar(Scanner scanner){
        Boolean bUnidade = true;


        System.out.println("Informe o nome do funcionario:");
        String nome = scanner.next();

        System.out.println("Informe o cpf do funcionario:");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<Unidade> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        funcionario.setCargo(cargoRepository.findById(cargoId).get());
        funcionario.setUnidades(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario salvo com sucesso!!!");
    }

    private List<Unidade> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<Unidade> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<Unidade> unidade = unidadeRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

//    public void atualizar(Scanner scanner){
//        System.out.println("Selecione uma das opções:");
//        listar();
//        Integer valor = scanner.nextInt();
//        System.out.println("Informe a nova descrição: ");
//        String descricao = scanner.next();
//        Optional<Unidade> unidade = repository.findById(valor);
//
//        if(unidade.isPresent()){
//            Unidade uni = unidade.get();
//            uni.setDescricao(descricao);
//            repository.save(uni);
//        }
//
//        System.out.println("Unidade atualizada com sucesso!!!");
//    }

    private void listar(Scanner scanner){
        System.out.println("Informe a página que deseja visualizar:");
        Integer pagina = scanner.nextInt();
        Pageable pageable = PageRequest.of(pagina, 4, Sort.by(Sort.Direction.ASC, "salario"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios.getNumber()+" de "+ funcionarios + " página(s)");
        System.out.println("Total de funcionários: "+funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> {
            System.out.println(funcionario);
        });
    }

//    private void deletar(Scanner scanner){
//        System.out.println("Selecione uma das opções para exclusão:");
//        listar();
//        Integer valor = scanner.nextInt();
//        repository.deleteById(valor);
//        System.out.println("Unidade excluído com sucesso!!!");
//    }
}
