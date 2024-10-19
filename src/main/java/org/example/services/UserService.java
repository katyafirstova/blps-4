package org.example.services;


import org.example.entity.User;
import org.example.model.UserRequest;
import org.example.model.UserRequestWithRole;
import org.example.repository.UserXMLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserXMLRepo userRepository;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user with username: '" + username + "' found");
        }
        return user;
    }

    public User createUserFromRequest(UserRequest userRequest){
        return new User(userRequest.getUsername(), userRequest.getPassword());
    }

    public User createUserWithRoleFromRequest(UserRequestWithRole userRequestWithRole){
        return new User(userRequestWithRole.getUsername(), userRequestWithRole.getPassword(),
                userRequestWithRole.getRole());
    }
}
