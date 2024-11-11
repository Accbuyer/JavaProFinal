package courses.JavaProFinal.controller;

import courses.JavaProFinal.dto.UserDTO;
import courses.JavaProFinal.entity.User;
import courses.JavaProFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.getOrCreateUserById(userId);
    }

    @GetMapping("/pay/{userId}/{amount}")
    public void pay(@PathVariable long userId, @PathVariable long amount) {
        userService.pay(userId, amount);
    }

    @GetMapping("/rollback/{userId}/{amount}")
    public void rollbackPay(@PathVariable long userId, @PathVariable long amount) {
        userService.rollbackPay(userId, amount);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable long userId, @RequestBody User updatedUser) {
        userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

}
