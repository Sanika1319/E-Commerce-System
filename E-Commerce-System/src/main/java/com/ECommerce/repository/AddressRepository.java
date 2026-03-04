package com.ECommerce.repository;

import com.ECommerce.Entities.Address;
import com.ECommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByUser(User user);
}
