package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.User;
import dev.pronunciationAppBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserActive(User user) {
        return user.isActive();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .map(existingUser -> userRepository.save(user));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public boolean deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public long getUserCount() {
        return userRepository.count();
    }

    public Optional<Integer> getUserLevel(String userId) {
        return userRepository.findById(userId).map(User::getLevel);
    }
}
