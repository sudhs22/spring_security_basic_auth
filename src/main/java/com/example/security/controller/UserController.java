package com.example.security.controller;

import com.example.security.dto.UserDTO;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_UPDATE')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = userRepository.save(fromDTOToEntity(userDTO));
        return fromEntityToDTO(user);
    }



    private UserDTO fromEntityToDTO(User createdUser) {
        UserDTO response = new UserDTO();
        response.setName(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        Set<String> roles = createdUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        response.setRoles(roles);
        return response;
    }

    private User fromDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roles = roleRepository.findByNameIn(new ArrayList<>(userDTO.getRoles()));
        user.setRoles(roles);
        return user;
    }


}
