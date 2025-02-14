package com.ecommerce.user.repository;

import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT new com.ecommerce.user.dto.UserResponseDto(u.id, u.userName, u.email, u.createdAt) FROM User u")
    Page<UserResponseDto> findAllProjectedToDto(Pageable pageable);

    Page<UserResponseDto> findByIsActiveFalse(Pageable pageable);
}
