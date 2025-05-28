package com.axconstantino.profile.exception;

public class InvalidAddressTypeException extends RuntimeException {
    public InvalidAddressTypeException(String type) {
        super("Invalid address type: " + type);
    }
}

