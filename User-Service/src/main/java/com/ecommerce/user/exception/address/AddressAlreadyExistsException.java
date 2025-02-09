package com.ecommerce.user.exception.address;

import com.ecommerce.user.model.AddressType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddressAlreadyExistsException extends RuntimeException{
    public AddressAlreadyExistsException(AddressType type) {
        super("An address of this type already exists: " + type);
    }
}
