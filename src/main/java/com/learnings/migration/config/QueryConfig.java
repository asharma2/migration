package com.learnings.migration.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class QueryConfig {

	@Bean
	public PropertiesFactoryBean migrationSqlQry() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocations(new ClassPathResource("queries/migration-sqls.xml"));
		return bean;
	}

}
