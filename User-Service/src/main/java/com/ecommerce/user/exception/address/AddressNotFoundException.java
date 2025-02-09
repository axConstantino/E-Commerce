package com.ecommerce.user.exception.address;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long addressId){
        super("Address not found with id: " + addressId);
    }
}
