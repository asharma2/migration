package com.learnings.migration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MigrationApp implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MigrationApp.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("NonOptionArgs: {}", args.getNonOptionArgs());
	}
}
