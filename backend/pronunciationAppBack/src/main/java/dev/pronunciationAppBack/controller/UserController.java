package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.User;
import dev.pronunciationAppBack.repository.UserRepository;
import dev.pronunciationAppBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/isActive")
    public ResponseEntity<String> isUserActive(@RequestBody User user){
        return userService.isUserActive(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping()
    public void deleteAllWords(){
        userService.deleteAllWords();
    }

/*    @DeleteMapping()
    public void deleteUser(@RequestBody User user){
        userService.deleteUser(user);
    }*/

    @DeleteMapping("/{id}")
    public void deleteUserById(@RequestParam String id){
        userService.getUserById(id);
    }


}
