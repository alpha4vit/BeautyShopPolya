package by.polly.beatyshop.modules.user.service;

import by.polly.beatyshop.modules.user.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getById(Long id);

    List<UserEntity> getAll();

    UserEntity save(UserEntity entity);

}
