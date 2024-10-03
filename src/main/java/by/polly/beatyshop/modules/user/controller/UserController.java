package by.polly.beatyshop.modules.user.controller;

import by.polly.beatyshop.modules.user.dto.User;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<User> getById(Long id);

}
