package com.dalvino.spring.service;

import com.dalvino.spring.orm.Cargo;
import com.dalvino.spring.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CrudCargoService {


    private final CargoRepository repository;
    private Boolean system = true;

    public CrudCargoService(CargoRepository repository) {
        this.repository = repository;
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
                    atualizar(scanner);
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void salvar(Scanner scanner){
        System.out.println("Informe a descrição do cargo:");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Cargo salvo com sucesso!!!");
    }

    public void atualizar(Scanner scanner){
        List<Cargo> cargoList = (List<Cargo>) repository.findAll();
        System.out.println("Selecione uma das opções:");
        cargoList.forEach(cargo -> {
            System.out.println(cargo.getId() + " - " + cargo.getDescricao());
        });
        Integer valor = scanner.nextInt();
        System.out.println("Informe a nova descrição para: ");
        String descricao = scanner.next();
        cargoList.forEach(cargo -> {
            if(cargo.getId().equals(valor)){
                cargo.setDescricao(descricao);
                repository.save(cargo);
            }
        });

        System.out.println("Cargo atualizada com sucesso!!!");
    }

    private void listar(){
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> {
            System.out.println(cargo);
        });
    }

    private void deletar(Scanner scanner){
        System.out.println("Selecione uma das opções para exclusão:");
        listar();
        Integer valor = scanner.nextInt();
        repository.deleteById(valor);
        System.out.println("Cargo excluído com sucesso!!!");
    }
}
