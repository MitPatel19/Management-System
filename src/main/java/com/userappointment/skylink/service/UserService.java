package com.userappointment.skylink.service;
import com.userappointment.skylink.models.User;
import com.userappointment.skylink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(User user) {
        // Encrypt the password before saving
        String base64EncodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(base64EncodedPassword);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(String userName, List<Long> userIds) {
        if (userName.equals("")){
            return userRepository.findAll();
        }else{
            if (userIds == null) {
                userIds = Collections.emptyList();
            }
//            System.out.println("this is user ids...!");
            Sort sortByFirstName = Sort.by(Sort.Direction.ASC, "firstName");
            Pageable pageableobj = PageRequest.of(1, 5).withSort(sortByFirstName);
            return userRepository.findByLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCaseOrIdIn(userName, userName, userIds, pageableobj);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        // Encrypt the password before saving updates
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
