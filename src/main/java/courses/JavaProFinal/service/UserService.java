package courses.JavaProFinal.service;

import courses.JavaProFinal.entity.User;
import courses.JavaProFinal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    public void resetPossiblePayments() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPossiblePayments(10000.00);
        }
        userRepository.saveAll(users);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long userId) {
        User user = userRepository.findById( userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUser(long userId, User updatedUser) {
        User user = userRepository.findById( userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setName(updatedUser.getName());
        user.setPossiblePayments(updatedUser.getPossiblePayments());
        userRepository.save(user);
    }

    public User getOrCreateUserById(long userId) {
        return userRepository.findById(userId)
                .orElseGet(() -> {
                    User newUser = new User(userId, "NewUser");
                    return userRepository.save(newUser);
                });
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void pay(long userId, long amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getPossiblePayments() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        user.setPossiblePayments(user.getPossiblePayments() - amount);
    }
}
