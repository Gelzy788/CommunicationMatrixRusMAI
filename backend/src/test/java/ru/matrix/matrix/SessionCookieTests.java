package ru.matrix.matrix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionCookieTests {

    @Autowired
    private Environment environment;

    @Test
    void sessionCookieShouldBeHttpOnly() {
        assertEquals(
                "true",
                environment.getProperty(
                        "server.servlet.session.cookie.http-only"
                ),
                "Cookie должна иметь атрибут HttpOnly"
        );
    }

    @Test
    void sessionCookieShouldBeSecure() {
        assertEquals(
                "true",
                environment.getProperty(
                        "server.servlet.session.cookie.secure"
                ),
                "Cookie должна иметь атрибут Secure"
        );
    }

    @Test
    void sessionCookieShouldUseSameSiteLax() {
        assertEquals(
                "Lax",
                environment.getProperty(
                        "server.servlet.session.cookie.same-site"
                ),
                "SameSite должен иметь значение Lax"
        );
    }

    @Test
    void sessionCookieShouldHaveRootPath() {
        assertEquals(
                "/",
                environment.getProperty(
                        "server.servlet.session.cookie.path"
                ),
                "Cookie должна быть доступна для всего приложения"
        );
    }

    @Test
    void sessionCookieShouldNotHavePersistentMaxAge() {
        assertNull(
                environment.getProperty(
                        "server.servlet.session.cookie.max-age"
                ),
                "Ожидается сессионная cookie без заданного Max-Age"
        );
    }
}
