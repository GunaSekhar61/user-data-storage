package com.user_data_storage.user_info_storage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
