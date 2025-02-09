package com.ecommerce.user.exception.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
}
