package com.axconstantino.profile.infrastructure.rest;

import com.axconstantino.profile.application.address.command.CreateAddressCommand;
import com.axconstantino.profile.application.address.command.UpdateAddressCommand;
import com.axconstantino.profile.application.address.usecase.*;
import com.axconstantino.profile.domain.entities.Address;
import com.axconstantino.profile.infrastructure.rest.dto.AddressCreateRequest;
import com.axconstantino.profile.infrastructure.rest.dto.AddressResponse;
import com.axconstantino.profile.infrastructure.rest.dto.UpdateAddressRequest;
import com.axconstantino.profile.infrastructure.rest.mapper.AddressRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final CreateAddress createAddress;
    private final UpdateAddress updateAddress;
    private final DeleteAddress deleteAddress;
    private final SetDefaultAddress setDefaultAddress;
    private final GetDefaultAddressById getDefaultAddress;
    private final GetAllAddressesByUserProfileId getAllAddresses;
    private final AddressRestMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody AddressCreateRequest createRequest, @AuthenticationPrincipal Jwt jwt) {
        String userKeycloakId = jwt.getSubject();
        CreateAddressCommand command = new CreateAddressCommand(userKeycloakId, mapper.toAddress(createRequest));
        createAddress.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@AuthenticationPrincipal Jwt jwt) {
        String userKeycloakId = jwt.getSubject();
        List<Address> addresses = getAllAddresses.execute(userKeycloakId);
        return ResponseEntity.ok(mapper.toDtoList(addresses));
    }

    @GetMapping("/default")
    public ResponseEntity<AddressResponse> getDefaultAddress(@AuthenticationPrincipal Jwt jwt) {
        String userKeycloakId = jwt.getSubject();
        Address defaultAddress = getDefaultAddress.execute(userKeycloakId);
        return ResponseEntity.ok(mapper.toDto(defaultAddress));
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<Void> updateAddress(@RequestBody UpdateAddressRequest updateRequest, @PathVariable UUID addressId) {
        UpdateAddressCommand command = new UpdateAddressCommand(addressId, mapper.toAddress(updateRequest));
        updateAddress.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/set-default/{addressId}")
    public ResponseEntity<Void> setDefaultAddress(@PathVariable UUID addressId, @AuthenticationPrincipal Jwt jwt) {
        String userKeycloakId = jwt.getSubject();
        setDefaultAddress.execute(userKeycloakId, addressId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID addressId) {
        deleteAddress.execute(addressId);
        return ResponseEntity.noContent().build();
    }
}
