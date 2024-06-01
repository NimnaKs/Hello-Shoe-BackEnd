package com.codeventlk.helloshoemanagementsystem.service;

import com.codeventlk.helloshoemanagementsystem.dto.ItemDTO;

import java.util.List;

public interface InventoryService {
    void saveItem(ItemDTO itemDTO);

    ItemDTO getItem(String id);

    void deleteItem(String id);

    void updateItem(String id, String itemDesc, String pic);

    List<ItemDTO> getAllItems();
}
