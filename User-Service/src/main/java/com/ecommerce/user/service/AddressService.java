package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressRequestDto;
import com.ecommerce.user.dto.AddressResponseDto;
import com.ecommerce.user.exception.AddressNotFoundException;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.mapper.AddressMapper;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.AddressRepository;
import com.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {
    public final AddressRepository addressRepository;
    public final UserRepository userRepository;
    public final AddressMapper mapper;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper mapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public AddressResponseDto getUserAddressById(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Address address = user.getAddresses().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        return mapper.toDto(address);
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return addressRepository.findByUserId(userId);
    }

    @Transactional
    public Address saveAddress(AddressRequestDto requestDto) {
        Address address = mapper.toEntity(requestDto);
        return addressRepository.save(address);
    }

    @Transactional
    public AddressResponseDto update(Long addressId, AddressRequestDto addressRequestDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        mapper.updateFromDto(addressRequestDto, address);
        Address updatedAddress = addressRepository.save(address);
        return mapper.toDto(updatedAddress);
    }

    public AddressResponseDto setDefaultAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Address address = addressRepository.findByUserIdAndId(userId, addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        addressRepository.unsetDefaultAddresses(userId, addressId);

        address.setDefault(true);
        Address updatedAddress = addressRepository.save(address);

        return mapper.toDto(address);
    }

    @Transactional
    public void delete(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));

        addressRepository.delete(address);
    }
}
