package com.user_data_storage.user_info_storage.controllers;

import com.user_data_storage.user_info_storage.models.User;
import com.user_data_storage.user_info_storage.repositery.CreateUserRecordRepo;
import com.user_data_storage.user_info_storage.security.JwtAuthenticationFilter;
import com.user_data_storage.user_info_storage.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173",methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class UserSignUp {
    @Autowired
    private CreateUserRecordRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public static class LoginRequest{
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class TokenResponse{
        private final String token;

        public TokenResponse(String token) {
            this.token = token;
        }
        public String getToken(){
            return token;
        }
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){
        String encodedPwd=passwordEncoder.encode(user.getPassword());
        String generateUserName= user.getEmail().split("@")[0];
        user.setPassword(encodedPwd);
        user.setUserName(generateUserName);
        repo.save(user);
        return user;
    }

    @CrossOrigin(origins = "http://localhost:5173",methods = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req){
        try {
         Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),req.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            throw new RuntimeException("Invalid credentials");
        }
        String token =jwtUtil.generateToken(req.getUsername());
        return new TokenResponse(token);
    }
}
