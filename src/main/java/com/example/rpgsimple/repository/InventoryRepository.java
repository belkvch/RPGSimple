package com.example.rpgsimple.repository;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import org.springframework.data.jpa.repository.*;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query(value = "select * from inventory where id = ?",nativeQuery = true)
    Inventory findInventoryByCharacter(Long id);
}
