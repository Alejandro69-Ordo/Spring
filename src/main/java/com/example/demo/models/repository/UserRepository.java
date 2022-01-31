package com.example.demo.models.repository;

import com.example.demo.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

