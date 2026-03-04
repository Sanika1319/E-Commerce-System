package com.ECommerce.Controller;

import com.ECommerce.Entities.User;
import com.ECommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return  ResponseEntity.ok("user deleted successfully");
    }
}
