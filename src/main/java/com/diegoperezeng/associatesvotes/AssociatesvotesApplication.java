package com.diegoperezeng.associatesvotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Import;

//import com.diegoperezeng.associatesvotes.config.DatabaseConfig;

@SpringBootApplication
//@Import(DatabaseConfig.class)
public class AssociatesvotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociatesvotesApplication.class, args);
	}

}
