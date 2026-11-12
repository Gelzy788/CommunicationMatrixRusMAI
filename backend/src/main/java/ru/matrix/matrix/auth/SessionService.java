package ru.matrix.matrix.auth;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

// Завершение сессии на всех устройствах
@Service
public class SessionService {
    private final SessionRegistry sessionRegistry;
    private final FindByIndexNameSessionRepository<? extends Session> sessions;

    public SessionService(SessionRegistry sessionRegistry,
                          FindByIndexNameSessionRepository<? extends Session> sessions) {
        this.sessionRegistry = sessionRegistry;
        this.sessions = sessions;
    }

    public void deleteAllSessions(String username) {
        sessions.findByPrincipalName(username).keySet()
                .forEach(sessions::deleteById);
    }
}
