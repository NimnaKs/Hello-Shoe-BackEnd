package com.codeventlk.helloshoemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "Variety")
@Data
public class VarietyEntity {
    @Id
    private String varietyCode;
    private String varietyDesc;
    @JsonIgnore
    @OneToMany(mappedBy = "varietyEntity",cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;
}
