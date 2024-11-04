package by.polly.beatyshop.modules.user.service.impl;

import by.polly.beatyshop.modules.user.entity.UserEntity;
import by.polly.beatyshop.modules.user.repository.UserRepository;
import by.polly.beatyshop.modules.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public UserEntity login(final UserEntity entity) {
        final var user = userRepository.findByEmail(entity.getEmail());
        if (passwordEncoder.encode(entity.getPassword()).equals(user.getPassword())){
            return user;
        }

        throw new IllegalArgumentException("Forbidden");
    }
}
