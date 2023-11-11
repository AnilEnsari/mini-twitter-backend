package com.example.twitter.service;



import com.example.twitter.entity.User;
import com.example.twitter.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.plaf.OptionPaneUI;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();

    User findByUserId(int id);

    User saveUser(User user);

    User deleteUser(int id);

    Optional<User> findUserByEmail(String email);

}