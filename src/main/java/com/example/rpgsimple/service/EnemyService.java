package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.*;

public interface EnemyService {

    Enemy save(Enemy enemy);

    Enemy findById(Long id);
}
