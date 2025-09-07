package com.user_data_storage.user_info_storage.security;

import com.user_data_storage.user_info_storage.models.Customer;
import com.user_data_storage.user_info_storage.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private CustomerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.warn("User not found: {}", "empty");
        Customer customer = repo.findByUserName(username);
        if (customer == null) {
            logger.warn("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        logger.info("User found: {}", username);
        System.out.println(customer.getUserName());
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getUserName())
                .password(customer.getPassword())
                .roles("USER")
                .build();
    }
}
