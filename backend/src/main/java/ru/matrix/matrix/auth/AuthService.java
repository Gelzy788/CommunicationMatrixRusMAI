package ru.matrix.matrix.auth;

import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;

import java.util.Locale;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest userData) {
        String email = userData.email().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ApiException(ErrorCode.EMAIL_TAKEN);
        }

        UserEntity user = new UserEntity(userData.fullName().trim(),
                email,
                passwordEncoder.encode(userData.password()),
                userData.consentAccepted());

        userRepository.save(user);
    }
}
