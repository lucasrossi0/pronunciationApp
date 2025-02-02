package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.User;
import dev.pronunciationAppBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/active")
    public ResponseEntity<String> isUserActive(@RequestBody User user) {
        HttpHeaders headers = getCommonHeaders("Check if user is active");
        return userService.isUserActive(user)
                ? new ResponseEntity<>("User is active", headers, HttpStatus.OK)
                : new ResponseEntity<>("User not active", headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        HttpHeaders headers = getCommonHeaders("Get all users");
        return users.isEmpty()
                ? new ResponseEntity<>(headers, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        HttpHeaders headers = getCommonHeaders("Get user by ID");
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, headers, HttpStatus.OK))
                .orElse(new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        HttpHeaders headers = getCommonHeaders("Create a new user");
        return new ResponseEntity<>(createdUser, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        HttpHeaders headers = getCommonHeaders("Update a user");
        return userService.updateUser(user)
                .map(updatedUser -> new ResponseEntity<>(updatedUser, headers, HttpStatus.OK))
                .orElse(new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        HttpHeaders headers = getCommonHeaders("Delete all users");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        HttpHeaders headers = getCommonHeaders("Delete user by ID");
        return userService.deleteById(id)
                ? new ResponseEntity<>(headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestBody User user) {
        HttpHeaders headers = getCommonHeaders("Delete specific user");
        return userService.deleteUser(user)
                ? new ResponseEntity<>(headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/level")
    public ResponseEntity<Integer> getUserLevel(@PathVariable String id) {
        HttpHeaders headers = getCommonHeaders("Get user level");
        return userService.getUserLevel(id)
                .map(level -> new ResponseEntity<>(level, headers, HttpStatus.OK))
                .orElse(new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "Spring Boot");
        headers.add("version", "1.0.0");
        headers.add("user-count", String.valueOf(userService.getUserCount()));
        headers.add("object", "users");
        return headers;
    }
}
