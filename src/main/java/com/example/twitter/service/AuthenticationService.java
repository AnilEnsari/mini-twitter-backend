package com.example.twitter.service;

import com.example.twitter.dto.LoggedinUserDto;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.TwitterException;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(encodedPassword);
        user1.setBirthday(user.getBirthday());
        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());
        user1.setUserName(user.getUsername());
        user1.setProfilePicture(user.getProfilePicture());
        user1.setProfileWallpaper(user.getProfileWallpaper());
        user1.setRegisterDate(user.getRegisterDate());
        user1.setPhone(user.getPhone());
        return user1;
    };

    public User login(LoggedinUserDto loginUserDto){
        Optional<User> optionalUser = userRepository.findUserByEmail(loginUserDto.email());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            System.out.println(user.getPassword() + loginUserDto.password());
            boolean isPasswordSame = passwordEncoder.matches(loginUserDto.password(),user.getPassword());
            if(isPasswordSame){
                return user;
            }
            throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
        }
        throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
    }
}