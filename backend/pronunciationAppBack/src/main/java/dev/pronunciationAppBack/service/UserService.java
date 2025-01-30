package dev.pronunciationAppBack.service;

import com.sun.net.httpserver.HttpsServer;
import dev.pronunciationAppBack.model.User;
import dev.pronunciationAppBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method returns the state of the User being passed as parameter
    public boolean isUserActive(User user){
        return user.isActive();
    }

    /*public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }*/

    public ResponseEntity<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
    }

    public ResponseEntity<?> getUserById(String id){
        Optional<User> userById = userRepository.findById(id);
        return userById.isPresent() ? ResponseEntity.ok(userById) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

/*    public ResponseEntity<?> getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        return Optional.ofNullable(user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }*/


/*    public ResponseEntity<User> getUserById(String id){
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }*/

/*    public ResponseEntity<User> createUser(User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }*/

    public ResponseEntity<?> createUser(User user){
        Optional<User> user1 = Optional.of(userRepository.save(user));
        return user1.isPresent() ? ResponseEntity.status(HttpStatus.CREATED).body(user1) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Error saving");
    }

}
