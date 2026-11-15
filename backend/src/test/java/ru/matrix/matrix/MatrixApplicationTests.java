package ru.matrix.matrix;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatrixApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Flyway flyway;

	@Test
	void contextLoads() {
	}

	@Test
	void databaseConnectionWorks() {
		Integer result = jdbcTemplate.queryForObject(
				"SELECT 1",
				Integer.class);

		assertEquals(1, result);
	}

	@Test
	void flywayMigrationsAreApplied() {
		assertTrue(
				flyway.info().applied().length > 0,
				"Должна быть применена хотя бы одна миграция");
	}

	@Test
	void thereAreNoPendingMigrations() {
		assertEquals(
				0,
				flyway.info().pending().length,
				"Не должно оставаться неприменённых миграций");
	}
}
