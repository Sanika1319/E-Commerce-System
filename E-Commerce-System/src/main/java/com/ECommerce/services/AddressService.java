package com.ECommerce.services;

import com.ECommerce.Entities.Address;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface AddressService {

    Address addAddress(Long userId, Address address);

    Address getAddressById(Long addressId);

    List<Address> getAddressesByUser(Long userId);

    Address updateAddress(Long userId, Long addressId, Address address);
    void deleteAddress(Long addressId);
}
