package com.learnings.migration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.learnings.migration.exception.MigrationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MigrationServiceImpl implements MigrationService<List<Map<String, Object>>> {

	@Override
	public List<Map<String, Object>> migrate(String fromSql, NamedParameterJdbcTemplate fromTemplate,
			MapSqlParameterSource fromQryParam, String toSql, NamedParameterJdbcTemplate toTemplate)
			throws MigrationException {
		long s = System.currentTimeMillis();
		List<Map<String, Object>> result = fromTemplate.queryForList(fromSql, fromQryParam);
		List<Map<String, Object>> out = new ArrayList<>();
		for (Map<String, Object> r : result) {
			MapSqlParameterSource sqlParamSource = new MapSqlParameterSource(r);
			List<Map<String, Object>> toResult = fromTemplate.queryForList(toSql, sqlParamSource);
			out.addAll(toResult);
		}
		log.info("FromQuery: {}, ToQuery: {}, Record: {}, Time: {}", fromSql, toSql, result.size(),
				(System.currentTimeMillis() - s));
		return out;
	}
}
