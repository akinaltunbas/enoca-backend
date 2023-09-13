package com.example.enocabackend.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enocabackend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String useranme);
}
