package com.learning.aerospike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

@SpringBootApplication
public class AerospikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AerospikeApplication.class, args);
	}

}
