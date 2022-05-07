package com.example.rpgsimple.service;

import com.example.rpgsimple.entity.Class;
import com.example.rpgsimple.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultClassService implements ClassService{
    private final ClassRepository classRepository;

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }
}
