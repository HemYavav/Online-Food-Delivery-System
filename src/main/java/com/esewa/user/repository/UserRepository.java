package com.esewa.user.repository;

import com.esewa.shared.enumcollection.Status;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserResponseDto> findUserByEmail(String email);
    Optional<User> findUsersByStatus(Status status);
    Optional<User> findUsersByIdAndStatus(int id, Status status);

    Optional<User>findByEmail(String email);

}
