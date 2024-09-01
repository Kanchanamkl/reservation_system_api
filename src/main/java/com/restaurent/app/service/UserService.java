package com.restaurent.app.service;


import com.restaurent.app.dto.AuthenticationReqDTO;
import com.restaurent.app.dto.AuthenticationResDTO;
import com.restaurent.app.dto.UserDTO;
import com.restaurent.app.entity.User;
import com.restaurent.app.exception.UserNotFoundException;
import com.restaurent.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kanchana_m
 */

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResDTO authenticateUser(AuthenticationReqDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getEmail()).orElseThrow();
        return AuthenticationResDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public AuthenticationResDTO createUser(UserDTO userDTO) {
        System.out.println("user-name :"+userDTO.getUsername());
        boolean isUserPresent = userRepository.findByUsername(userDTO.getUsername()).isPresent();

        if(isUserPresent){
            return null;
        }else{

            User user = User.builder()
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(userDTO.getRole())
                    .build();

            userRepository.save(user);
            return AuthenticationResDTO.builder()
                    .username(userDTO.getUsername())
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .role(userDTO.getRole().toString())
                    .build();
        }



    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setUsername(userDTO.getUsername());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Sorry, no user found with the Id :" +id));
    }


    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("Sorry, user not found");

        }
        userRepository.deleteById(id);
        return true;
    }
    private boolean userAlreadyExists(String email) {
        return userRepository.findByUsername(email).isPresent();
    }
}
