package com.user_data_storage.user_info_storage.services;

import com.user_data_storage.user_info_storage.dto.CustomerDto;
import com.user_data_storage.user_info_storage.models.Customer;
import com.user_data_storage.user_info_storage.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder ;

    CustomerService(CustomerRepository customerRepository,PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createCustomer(CustomerDto customerDto){

        String generateUserName= customerDto.getEmail().split("@")[0];
        String encodedPwd=passwordEncoder.encode(customerDto.getPassword());
        Customer customer = Customer.builder().firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .userName(generateUserName)
                .email(customerDto.getEmail())
                .password(encodedPwd).build();

        return customerRepository.save(customer);

    }


}
