package com.ECommerce.Controller;

import com.ECommerce.Entities.Address;
import com.ECommerce.services.AddressService;
import org.apache.catalina.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addAddress/{userId}")
    public ResponseEntity<Address> addAddress(@PathVariable Long userId,@RequestBody Address address){
        return new ResponseEntity<>(addressService.addAddress(userId, address), HttpStatus.CREATED);
    }

    @GetMapping("/getAddressById/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long addressId){
        return new ResponseEntity<>(addressService.getAddressById(addressId),HttpStatus.OK);
    }

    @GetMapping("/getAddressesByUser/{userId}")
    public ResponseEntity<List<Address>> getAddressesByUser(@PathVariable Long userId){
        return new ResponseEntity<>(addressService.getAddressesByUser(userId),HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{userId}/{addressId}")
    public ResponseEntity<Address>updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @RequestBody Address address){
        return new ResponseEntity<>(
                addressService.
                        updateAddress(
                                userId,
                                addressId,
                                address)
                ,HttpStatus.OK);
    }


    @DeleteMapping("/deleteAddress/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address Deleted Successfully");
    }


}
