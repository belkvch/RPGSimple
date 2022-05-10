package com.example.rpgsimple.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/death")
public class DeathController {
    @GetMapping
    public String death() {
        return "death";
    }
}
