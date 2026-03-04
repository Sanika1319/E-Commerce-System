package com.ECommerce.services.serviceImpl;

import com.ECommerce.Entities.Address;
import com.ECommerce.Entities.User;
import com.ECommerce.repository.AddressRepository;
import com.ECommerce.repository.UserRepository;
import com.ECommerce.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Address addAddress(Long userId, Address address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(()->new RuntimeException("Address Not Found "));
    }

    @Override
    public List<Address> getAddressesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found: " + userId));

        return addressRepository.findByUser(user);
    }

    @Override
    public Address updateAddress(Long userId, Long addressId, Address address) {
        // 1️⃣ Check if address exists
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address Not Found: " + addressId));

        // 2️⃣ Check if address belongs to this user
        if (!existingAddress.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this address");
        }

        // 3️⃣ Update fields
        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setPincode(address.getPincode());
        existingAddress.setCountry(address.getCountry());

        // 4️⃣ Save updated address
        return addressRepository.save(existingAddress);
        }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Address Not Found"));
        addressRepository.delete(address);

    }
}
