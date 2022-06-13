package com.learnings.migration.service;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.learnings.migration.exception.MigrationException;

public interface MigrationService<R> {

	R migrate(String fromSql, NamedParameterJdbcTemplate fromTemplate, MapSqlParameterSource fromQryParam, String toSql,
			NamedParameterJdbcTemplate toTemplate) throws MigrationException;
}
