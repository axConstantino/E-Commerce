package com.ecommerce.user.exception.address;

public class AddressLimitExceededException extends RuntimeException{
    public AddressLimitExceededException(Long userId) {
        super("The user with ID " + userId + " has already reached the maximum limit of 3 addresses.");
    }
}
