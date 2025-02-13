package com.ecommerce.user.repository;

import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Cacheable(key = "#email")
    @Query("SELECT u FROM User u  WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("SELECT new com.ecommerce.user.dto.UserResponseDto(u.id, u.userName, u.email, u.createdAt) FROM User u")
    Page<UserResponseDto> findAllProjectedToDto(Pageable pageable);

}
