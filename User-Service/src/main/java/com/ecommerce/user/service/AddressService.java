package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.exception.address.AddressAlreadyExistsException;
import com.ecommerce.user.exception.address.AddressLimitExceededException;
import com.ecommerce.user.exception.address.AddressNotFoundException;
import com.ecommerce.user.exception.user.UserNotFoundException;
import com.ecommerce.user.mapper.AddressMapper;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.AddressRepository;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    public final AddressRepository addressRepository;
    public final UserRepository userRepository;
    public final AddressMapper mapper;

    @Transactional(readOnly = true)
    public AddressResponseDto getSpecificUserAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Address address = addressRepository.findByUserIdAndId(userId, addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        return mapper.toDto(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponseDto> getAddressesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return mapper.toDtoList(addressRepository.findByUserId(userId));
    }

    @Transactional
    public AddressResponseDto saveAddress(Long userId, AddressRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        addressRepository.findByUserIdAndAddressType(userId, requestDto.getAddressType())
                .ifPresent(existing -> {
                    throw new AddressAlreadyExistsException(requestDto.getAddressType());
                });

        if (user.getAddresses() != null && user.getAddresses().size() >= 3) {
            throw new AddressLimitExceededException(userId);
        }

        int addressCount = addressRepository.countByUser(user);
        if (addressCount >= 3){
            throw new AddressLimitExceededException(userId);
        }

        Address address = mapper.toEntity(requestDto);
        address.assignUser(user);
        Address savedAddress = addressRepository.save(address);

        if (savedAddress.isDefault()){
            addressRepository.unsetDefaultAddresses(userId, savedAddress.getId());
        }

        return mapper.toDto(savedAddress);
    }

    @Transactional
    public AddressResponseDto update(Long userId, Long addressId, AddressRequestDto addressRequestDto) {
        Address existingAddress = addressRepository.findByUserIdAndId(userId, addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        Address updatedAddress = mapper.updateFromDto(existingAddress, addressRequestDto);

        if (updatedAddress.isDefault()) {
            addressRepository.unsetDefaultAddresses(userId, updatedAddress.getId());
        }

        Address savedAddress = addressRepository.save(updatedAddress);
        return mapper.toDto(savedAddress);
    }

    @Transactional
    public AddressResponseDto setDefaultAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Address address = addressRepository.findByUserIdAndId(userId, addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        addressRepository.unsetDefaultAddresses(userId, addressId);
        address.markAsDefault();

        Address updatedAddress = addressRepository.save(address);

        return mapper.toDto(updatedAddress);
    }

    @Transactional
    public void delete(Long userId, Long addressId) {
        Address address = addressRepository.findByUserIdAndId(userId, addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        addressRepository.delete(address);
    }
}
