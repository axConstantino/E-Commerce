package com.ecommerce.user.service;

import com.ecommerce.user.dto.SearchRequestDto;
import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.exception.user.UserNotFoundException;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.model.Role;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository repository;
    public final UserMapper mapper;

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return repository.findAllProjectedToDto(pageable);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> searchUsers(SearchRequestDto searchRequest, Pageable pageable) {
        String name = searchRequest.getName();
        String email = searchRequest.getEmail();
        String phone = searchRequest.getPhone();
        return repository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (email != null && !email.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }

            if (phone != null && !phone.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(mapper::toDto);
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = mapper.toEntity(requestDto);
        User saveUser = repository.save(user);
        return mapper.toDto(saveUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        User updatedUser = mapper.updateFromDto(user, requestDto);
        repository.save(updatedUser);
        return mapper.toDto(updatedUser);
    }

    @Transactional
    public void updateUserRole(Long userId, Role newRole) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.changeRole(newRole);
        repository.save(user);
    }

    @Transactional
    public void softDelete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.deactivate();
        repository.save(user);
    }

    @Transactional
    public void hardDelete(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        repository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> getInactiveUsersList(Pageable pageable) {
        return repository.findByIsActiveFalse(pageable);
    }
}
