package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserRequestDto;
import com.ecommerce.user.dto.UserResponseDto;
import com.ecommerce.user.exception.user.UserNotFoundException;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<UserResponseDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllProjectedToDto(pageable);
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = mapper.toEntity(requestDto);
        User saveUser = repository.save(user);
        return mapper.toDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        User updatedUser = mapper.updateFromDto(user, requestDto);
        return mapper.toDto(updatedUser);
    }

    @Transactional
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
