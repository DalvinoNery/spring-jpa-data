package com.dalvino.spring.repository;

import com.dalvino.spring.orm.Unidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Integer> {
}
