package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.*;

public interface InventoryService {
    Inventory save(Inventory inventory);

    Inventory findInventoryByCharacter(Long id);
}
