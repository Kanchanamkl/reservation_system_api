package com.restaurent.app.controller;


import com.restaurent.app.dto.AuthenticationReqDTO;
import com.restaurent.app.dto.AuthenticationResDTO;
import com.restaurent.app.dto.UserDTO;
import com.restaurent.app.entity.User;
import com.restaurent.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kanchana_m
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private  UserService userService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResDTO>authenticateUser(@RequestBody AuthenticationReqDTO request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }
    @GetMapping("/get_users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/create-user")
    public  ResponseEntity<AuthenticationResDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
    @PutMapping("/update-user")
    public ResponseEntity<User> updateUser(@RequestParam Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    @GetMapping("/get-user")
    public User getUserById(@RequestParam Long id){
        return userService.getUserById(id);
    }


}
