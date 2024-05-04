package wtwt.domain.user.application;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.user.application.dto.request.SignUpReq;
import wtwt.domain.user.infrastructure.UserRepository;
import wtwt.domain.user.model.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpReq request) {
        if (isDuplicatedEmail(request.email())) {
            throw new EntityExistsException("이미 사용중인 이메일입니다.");
        }

        String encrypted = passwordEncoder.encode(request.password());
        User user = User.builder()
            .email(request.email())
            .password(encrypted)
            .build();

        userRepository.save(user);

        return user.getId();
    }

    private boolean isDuplicatedEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
