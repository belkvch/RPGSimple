package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class DefaultInventoryService implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findInventoryByCharacter(Long id) {
        return inventoryRepository.findInventoryByCharacter(id);
    }
}
