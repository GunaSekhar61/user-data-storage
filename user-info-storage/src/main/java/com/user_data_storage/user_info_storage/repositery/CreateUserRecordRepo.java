package com.user_data_storage.user_info_storage.repositery;

import com.user_data_storage.user_info_storage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateUserRecordRepo extends JpaRepository<User,Long> {

    User findByUserName(String userName);
}
