package com.user_data_storage.user_info_storage.repositery;

import com.user_data_storage.user_info_storage.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByUserName(String userName);

    Optional<Customer> findByEmail(String email);
}
