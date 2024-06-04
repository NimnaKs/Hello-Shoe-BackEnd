package com.codeventlk.helloshoemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Branch")
@Data
public class BranchEntity {
    @Id
    private String branchId;
    @Column(unique = true)
    private String branchName;
}
