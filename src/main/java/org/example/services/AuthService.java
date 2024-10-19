package org.example.services;


import org.example.entity.User;
import org.example.repository.UserXMLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserXMLRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public boolean authenticateUser(User user) {
        try {
            User foundByUsername = userRepository.findByUsername(user.getUsername());
            return passwordEncoder.matches(user.getPassword(), foundByUsername.getPassword());
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public boolean registerUser(User user) {

        User u = userRepository.findByUsername(user.getUsername());
        if(u == null){
            userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getRole()));
            return true;
        }
        else {
            return false;
        }
    }

}
