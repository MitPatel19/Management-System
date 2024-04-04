package com.userappointment.skylink.configuration;
import com.userappointment.skylink.models.User;
import com.userappointment.skylink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String[] GetUserCredentialDeatils(String basicAuthValue){

        // Decode the Basic Authentication value
        byte[] decodedBytes = Base64.getDecoder().decode(basicAuthValue);
        String decodedValue = new String(decodedBytes, StandardCharsets.UTF_8);
//        System.out.println("decodedValue - " + decodedValue);

        // Extract the username and password from the decoded value
        return decodedValue.split(":");
    }

    public User GetUserDetails(String basicAuthValue) {
        // Decode the Basic Authentication value
        byte[] decodedBytes = Base64.getDecoder().decode(basicAuthValue);
        String decodedValue = new String(decodedBytes, StandardCharsets.UTF_8);

        // Extract the username and password from the decoded value
        String[] credentials = decodedValue.split(":");
        String username = credentials[0];
        String password = credentials[1];

        User user = userRepository.findByEmail(username);

        System.out.println(user + "-user");
        if (user != null) {
            String decryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
//            System.out.println(decryptedPassword + "-decryptedPassword");
            if (decryptedPassword.equals(user.getPassword())) {
//                System.out.println("decrypted password matches!");
                return user;
            }
        }

        return null;
    }


}
