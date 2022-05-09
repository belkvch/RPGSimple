package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class DefaultEnemyService implements EnemyService{
    private final EnemyRepository enemyRepository;

    @Override
    public Enemy save(Enemy enemy) {
        return enemyRepository.save(enemy);
    }

    @Override
    public Enemy findById(Long id) {
        return enemyRepository.getById(id);
    }
}
