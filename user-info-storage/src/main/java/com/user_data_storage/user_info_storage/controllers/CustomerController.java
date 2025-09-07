package com.user_data_storage.user_info_storage.controllers;

import com.user_data_storage.user_info_storage.dto.CustomerDto;
import com.user_data_storage.user_info_storage.dto.CustomerResponseDto;
import com.user_data_storage.user_info_storage.dto.LoginRequestDto;
import com.user_data_storage.user_info_storage.models.Customer;
import com.user_data_storage.user_info_storage.repository.CustomerRepository;
import com.user_data_storage.user_info_storage.security.JwtUtil;
import com.user_data_storage.user_info_storage.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173",methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;


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
    public ResponseEntity<CustomerResponseDto> registerUser(@RequestBody CustomerDto customerDto){

            Optional<Customer> customerWithSameEmail = customerRepository.findByEmail(customerDto.getEmail());
            if(customerWithSameEmail.isEmpty()){
                Customer customer = customerService.createCustomer(customerDto);
                return new ResponseEntity<>(CustomerResponseDto.customerToCustomerResponseDto(customer), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(CustomerResponseDto.customerToCustomerResponseDto(customerWithSameEmail.get()),HttpStatus.CONFLICT);
            }

    }

    @CrossOrigin(origins = "http://localhost:5173",methods = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequestDto req){
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
