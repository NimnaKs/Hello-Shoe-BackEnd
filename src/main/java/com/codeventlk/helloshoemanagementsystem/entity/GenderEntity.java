package com.codeventlk.helloshoemanagementsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "gender")
public class GenderEntity {
    @Id
    private String genderCode;
    private String genderDesc;
    @OneToMany(mappedBy = "genderEntity",cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;

}
