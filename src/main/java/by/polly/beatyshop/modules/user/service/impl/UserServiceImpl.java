package by.polly.beatyshop.modules.user.service.impl;

import by.polly.beatyshop.modules.user.entity.UserEntity;
import by.polly.beatyshop.modules.user.repository.UserRepository;
import by.polly.beatyshop.modules.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with this id not found!"));
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        if (userRepository.findByEmail(entity.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public UserEntity login(final UserEntity request) {
        final var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with this email not found!")
        );
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return user;
        }

        throw new IllegalArgumentException("Forbidden");
    }
}
