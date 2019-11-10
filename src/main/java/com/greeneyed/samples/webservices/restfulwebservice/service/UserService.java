package com.greeneyed.samples.webservices.restfulwebservice.service;

import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import com.greeneyed.samples.webservices.restfulwebservice.exception.NotFoundException;
import com.greeneyed.samples.webservices.restfulwebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with id %d was not found", id)));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
