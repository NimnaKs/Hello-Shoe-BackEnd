package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.BranchDTO;
import com.codeventlk.helloshoemanagementsystem.dto.CustomerDTO;
import com.codeventlk.helloshoemanagementsystem.exception.InvalidException;

import java.util.List;

public interface BranchService {
    void saveBranch(BranchDTO branchDTO) throws InvalidException;

    List<BranchDTO> getAllBranches();
}
