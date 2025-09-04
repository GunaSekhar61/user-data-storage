package com.user_data_storage.user_info_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.user_data_storage.user_info_storage")
@EnableJpaAuditing
@EnableScheduling
public class UserInfoStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInfoStorageApplication.class, args);
	}

}
