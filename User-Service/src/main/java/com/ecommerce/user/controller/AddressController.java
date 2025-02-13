package com.ecommerce.user.controller;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AddressController.URL)
@Validated
@RequiredArgsConstructor
public class AddressController {
    protected static final String URL = "/api/v1/users/me/addresses";
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new address for a user.")
    public ResponseEntity<AddressResponseDto> createNewAddress(@RequestHeader(UserController.userHeaderId) String userId, @Valid @RequestBody AddressRequestDto requestDto) {
        Long id = Long.parseLong(userId);
        AddressResponseDto address = addressService.saveAddress(id, requestDto);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    @Operation(summary = "Get a list of user addresses.")
    public ResponseEntity<List<AddressResponseDto>> getAllUserAddresses(@RequestHeader(UserController.userHeaderId) String userId) {
        Long id = Long.parseLong(userId);
        List<AddressResponseDto> userAddresses = addressService.getAddressesByUserId(id);
        return ResponseEntity.ok(userAddresses);
    }

    @GetMapping("/{addressId}")
    @Operation(summary = "Get specific user address.")
    public AddressResponseDto getSpecificUserAddress(@RequestHeader(UserController.userHeaderId)String userId, @PathVariable Long addressId) {
        Long id = Long.parseLong(userId);
        return addressService.getSpecificUserAddress(id, addressId);
    }

    @PutMapping()
    @Operation(summary = "Update an existing address")
    public AddressResponseDto updateAddress(@RequestHeader(UserController.userHeaderId)String userId, @PathVariable Long addressId, @Valid @RequestBody AddressRequestDto requestDto) {
        Long id = Long.parseLong(userId);
        return addressService.update(id, addressId, requestDto);
    }

    @PutMapping("/set-default")
    @Operation(summary = "Set address as default")
    public AddressResponseDto setDefault(@RequestHeader(UserController.userHeaderId)String userId, @PathVariable Long addressId) {
        Long id = Long.parseLong(userId);
        return addressService.setDefaultAddress(id, addressId);
    }

    @DeleteMapping()
    @Operation(summary = "Delete an address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@RequestHeader(UserController.userHeaderId) String userId, @PathVariable Long addressId) {
        Long id = Long.parseLong(userId);
        addressService.delete(id, addressId);
    }
}
