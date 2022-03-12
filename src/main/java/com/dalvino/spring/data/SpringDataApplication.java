package com.dalvino.spring.data;


import com.dalvino.spring.orm.Cargo;
import com.dalvino.spring.repository.CargoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com/dalvino/spring/orm")
@EnableJpaRepositories("com.dalvino.spring.repository")
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private CargoRepository repository;

	public SpringDataApplication( CargoRepository repository){
		this.repository  = repository ;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");
		repository.save(cargo);
	}
}
