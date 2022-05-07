package com.example.rpgsimple.controller;
import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/welcomePage")
public class WelcomePageController {
    private final DefaultCharacterService characterService;

    @GetMapping
    public String registration() {
        return "welcomePage";
    }

    @GetMapping("/authenticated")
    public String registration(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterCheck = characterService.findUserByIdUser(userCurrent.getId());
        boolean isCharacter = false;
        if (characterCheck == null) {
            isCharacter = true;
        }
        model.addAttribute("isCharacter", isCharacter);
        return "welcomePage";
    }
}
