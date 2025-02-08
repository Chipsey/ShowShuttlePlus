package com.show_shuttle_plus.demo.service;

import com.show_shuttle_plus.demo.entity.User;
import com.show_shuttle_plus.demo.exception.UserNotFoundException;
import com.show_shuttle_plus.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+ id));
    }

    //Update User
    public User updateUser(Long id, User newUser) {
        User existingUser = getUserById(id);
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setUsername(newUser.getUsername());

        return userRepository.save(existingUser);
    }

    //delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
