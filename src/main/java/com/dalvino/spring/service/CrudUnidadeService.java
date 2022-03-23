package com.dalvino.spring.service;


import com.dalvino.spring.orm.Cargo;
import com.dalvino.spring.orm.Unidade;
import com.dalvino.spring.repository.CargoRepository;
import com.dalvino.spring.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeService {
    private final UnidadeRepository repository;
    private Boolean system = true;

    public CrudUnidadeService(UnidadeRepository repository) {
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
        System.out.println("Informe a descrição da unidade:");
        String descricao = scanner.next();
        System.out.println("Informe o endereço da unidade"+descricao+":");
        String endereco = scanner.next();
        Unidade unidade = new Unidade();
        unidade.setDescricao(descricao);
        unidade.setEndereco(endereco);
        repository.save(unidade);
        System.out.println("Unidade salva com sucesso!!!");
    }

    public void atualizar(Scanner scanner){
        System.out.println("Selecione uma das opções:");
        listar();
        Integer valor = scanner.nextInt();
        System.out.println("Informe a nova descrição: ");
        String descricao = scanner.next();
        Optional<Unidade> unidade = repository.findById(valor);

        if(unidade.isPresent()){
            Unidade uni = unidade.get();
            uni.setDescricao(descricao);
            repository.save(uni);
        }

        System.out.println("Unidade atualizada com sucesso!!!");
    }

    private void listar(){
        Iterable<Unidade> unidades = repository.findAll();
        unidades.forEach(unidade -> {
            System.out.println(unidade);
        });
    }

    private void deletar(Scanner scanner){
        System.out.println("Selecione uma das opções para exclusão:");
        listar();
        Integer valor = scanner.nextInt();
        repository.deleteById(valor);
        System.out.println("Unidade excluído com sucesso!!!");
    }
}
