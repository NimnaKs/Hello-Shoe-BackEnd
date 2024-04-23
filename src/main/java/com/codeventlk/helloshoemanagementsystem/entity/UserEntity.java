package com.codeventlk.helloshoemanagementsystem.entity;

import com.codeventlk.helloshoemanagementsystem.Enum.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "User")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private Role role;
    @OneToMany (mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}
