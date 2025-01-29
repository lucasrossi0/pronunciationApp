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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello guys", HttpStatus.OK);
    }

    @PostMapping("/isActive")
    public String isUserActive(@RequestBody User user){
        return userService.isUserActive(user) ? "Active" : "Not active";
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}
