package org.example.global.config.security.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    public String encodePassword(User user){
        return passwordEncoder.encode(user.getPassword());
    }

    public boolean matchPassword(String loginPassword, String encodedPassword) {
        return passwordEncoder.matches(loginPassword, encodedPassword);
    }
}
