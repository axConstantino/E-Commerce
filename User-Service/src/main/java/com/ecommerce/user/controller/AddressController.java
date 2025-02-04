package com.ecommerce.user.controller;

import com.ecommerce.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AddressController.URL)
public class AddressController {
    protected static final String URL = "/api/v1/address";

    @Autowired
    private AddressService addressService;


}
