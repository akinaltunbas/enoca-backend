package com.example.enocabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enocabackend.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
}
