package com.example.rpgsimple.controller;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.lang.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cave")
public class CaveController {
    private final DefaultCharacterService characterService;
    private final DefaultInventoryService inventoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CaveController.class);

    @GetMapping
    public String cavePage(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
        model.addAttribute("character", characterUser);
        Inventory inventoryByCharacter = inventoryService.findInventoryByCharacter(characterUser.getInventoryCharacter().getId());
        model.addAttribute("inventory", inventoryByCharacter);
        if (inventoryByCharacter.isKey()) {
            return "cave";
        } else {
            model.addAttribute("keyError","You don't have a key");
            LOGGER.info("user hasn't got a key");
            return "ruins";
        }
    }
}
