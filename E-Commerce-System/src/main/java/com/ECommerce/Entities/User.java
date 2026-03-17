package com.ECommerce.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String status = "ACTIVE";
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Orders> orders;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Cart cart;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private WishList wishList;
    @PrePersist
    protected void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

}
