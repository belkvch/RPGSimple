package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.Character;

public interface CharacterService {
    Character findById(Long Id);

    Character findUserByIdUser(Long id);

    Character save(Character character);
}
