package com.example.rpgsimple.repository;

import com.example.rpgsimple.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
