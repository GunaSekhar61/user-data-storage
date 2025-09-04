package com.user_data_storage.user_info_storage.dto;


import com.user_data_storage.user_info_storage.models.Customer;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private Date createdDate;

    private Date modifiedDate;


    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer) {

        return CustomerResponseDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .createdDate(customer.getCreatedDate())
                .modifiedDate(customer.getModifiedDate())
                .build();
    }

}
