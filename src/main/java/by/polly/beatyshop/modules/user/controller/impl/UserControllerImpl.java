package by.polly.beatyshop.modules.user.controller.impl;

import by.polly.beatyshop.modules.user.controller.UserController;
import by.polly.beatyshop.modules.user.dto.User;
import by.polly.beatyshop.modules.user.mapper.UserMapper;
import by.polly.beatyshop.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PostMapping("/login")
    public User login(@RequestBody User userRequest) {
        final var user = userMapper.toEntity(userRequest);
        return userMapper.toDTO(userService.login(user));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User userRequest) {
        final var user = userMapper.toEntity(userRequest);
        return ResponseEntity.ok(userMapper.toDTO(userService.save(user)));
    }

}
