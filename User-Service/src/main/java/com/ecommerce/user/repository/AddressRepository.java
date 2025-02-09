package com.ecommerce.user.repository;

import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    List<Address> findByUserId(@Param("userId") Long userId);

    Optional<Address> findByUserIdAndId(Long userId, Long addressId);

    Optional<Address> findByUserIdAndAddressType(Long userId, AddressType addressType);

    @Modifying
    @Query("UPDATE Address a SET a.isDefault = false WHERE a.user.id = :userId AND a.id != :addressId")
    void unsetDefaultAddresses(@Param("userId") Long userId, @Param("addressId") Long addressId);
}
