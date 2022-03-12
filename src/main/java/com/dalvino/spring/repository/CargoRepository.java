package com.dalvino.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dalvino.spring.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
}
