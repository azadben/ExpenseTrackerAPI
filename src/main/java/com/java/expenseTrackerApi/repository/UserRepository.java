package com.java.expenseTrackerApi.repository;

import com.java.expenseTrackerApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

}
