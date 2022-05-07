package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class DefaultCharacterService implements CharacterService {
    private final CharacterRepository characterRepository;


    @Override
    public Character findById(Long Id) {
        return characterRepository.getById(Id);
    }

    @Override
    public Character findUserByIdUser(Long id) {
        return characterRepository.findUserByIdUser(id);
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }
}
