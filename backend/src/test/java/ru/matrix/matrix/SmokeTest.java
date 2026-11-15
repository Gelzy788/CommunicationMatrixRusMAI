package ru.matrix.matrix;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SmokeTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Flyway flyway;

    @Test
    void applicationSmokeTest() {

        // 1. Проверка подключения к PostgreSQL
        Integer result = jdbcTemplate.queryForObject(
                "SELECT 1",
                Integer.class
        );

        assertEquals(1, result,
                "PostgreSQL должен успешно выполнять запросы");

        // 2. Проверка наличия применённых миграций
        assertTrue(
                flyway.info().applied().length > 0,
                "Должна быть применена хотя бы одна миграция"
        );

        // 3. Проверка отсутствия ожидающих миграций
        assertEquals(
                0,
                flyway.info().pending().length,
                "Не должно оставаться неприменённых миграций"
        );
    }
}

