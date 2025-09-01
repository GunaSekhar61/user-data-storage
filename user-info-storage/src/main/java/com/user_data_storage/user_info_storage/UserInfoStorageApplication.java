package com.user_data_storage.user_info_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.user_data_storage.user_info_storage")
public class UserInfoStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInfoStorageApplication.class, args);
	}

}
