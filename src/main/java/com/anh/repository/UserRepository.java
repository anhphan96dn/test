package com.anh.repository;

import com.anh.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByEmail(String email);
}
