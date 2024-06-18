package com.example.abilitycheck.Controller;

import com.example.abilitycheck.Model.User;
import com.example.abilitycheck.Repository.UserRepository;
import com.example.abilitycheck.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/yeah")
    public String testControllerFun(){
        return "yeah bro we on for real";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById (@PathVariable Long id){
        User thisUser = userRepository.findById(id).orElse(null);
        if (thisUser == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(thisUser);
        }
    }



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user) {
        User createUser = userRepository.save(user);
        return ResponseEntity.ok(createUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@PathVariable Long id, @RequestBody User user) {
        User thisUser = userRepository.findById(id).orElse(null);
        if (thisUser == null) {
            return ResponseEntity.ok(null);
        } else {
            thisUser.setName(user.getName());
            thisUser.setEmail(user.getEmail());
            userRepository.save(thisUser);
            return ResponseEntity.ok(thisUser);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }



}
