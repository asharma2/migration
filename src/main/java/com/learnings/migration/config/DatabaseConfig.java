package com.learnings.migration.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource fromDatasource() {
		return DataSourceBuilder.create() //
				.url(env.getProperty("spring.from.datasource.url"))
				.username(env.getProperty("spring.from.datasource.username"))
				.password(env.getProperty("spring.from.datasource.password"))
				.driverClassName(env.getProperty("spring.from.datasource.driver-class-name"))
				.type(HikariDataSource.class) //
				.build();
	}

	@Primary
	@Bean(name = "fromNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate fromNamedParameterJdbcTemplate(
			@Qualifier("fromDatasource") DataSource fromDatasource) {
		return new NamedParameterJdbcTemplate(fromDatasource);
	}

	@Bean
	public DataSource toDatasource() {
		return DataSourceBuilder.create() //
				.url(env.getProperty("spring.to.datasource.url"))
				.username(env.getProperty("spring.to.datasource.username"))
				.password(env.getProperty("spring.to.datasource.password"))
				.driverClassName(env.getProperty("spring.to.datasource.driver-class-name")).type(HikariDataSource.class) //
				.build();
	}

	@Primary
	@Bean(name = "toNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate toNamedParameterJdbcTemplate(@Qualifier("toDatasource") DataSource toDatasource) {
		return new NamedParameterJdbcTemplate(toDatasource);
	}

}
