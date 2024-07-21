package com.handihub.auth_service.repository;

import com.handihub.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    String findByEmail(String email);
    Boolean existsByEmail(String email);
}
