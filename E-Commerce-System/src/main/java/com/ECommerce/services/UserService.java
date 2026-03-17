package com.ECommerce.services;

import com.ECommerce.Entities.User;

import java.util.List;

public interface UserService {
    //    Registration
    User saveUser(User user);
    User deactivateUser(Long userId);
    User activateUser(Long userId);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByEmail(String email);

    void deleteUser(Long userId);
}
