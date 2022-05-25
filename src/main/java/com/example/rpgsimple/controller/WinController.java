package com.example.rpgsimple.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/win")
public class WinController {
    @GetMapping
    public String win() {
        return "win";
    }
}
