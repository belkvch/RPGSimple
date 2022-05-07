package com.example.rpgsimple.repository;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import org.springframework.data.jpa.repository.*;


public interface CharacterRepository extends JpaRepository<Character, Long>  {
    @Query(value = "select * from character where users = ?",nativeQuery = true)
    Character findUserByIdUser(Long id);
}
