package com.example.rpgsimple.controller;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.lang.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/witchsLair")
public class WitchsLairController {
    private final DefaultCharacterService characterService;
    private final DefaultInventoryService inventoryService;

    @GetMapping
    public String witchsLairPage(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
        model.addAttribute("character", characterUser);
        Inventory inventoryByCharacter = inventoryService.findInventoryByCharacter(characterUser.getInventoryCharacter().getId());
        model.addAttribute("inventory", inventoryByCharacter);
        return "witchsLair";
    }

    @PostMapping
    public String agreeQuest(@RequestParam(value = "id") Long id) {
        Character currentCharacter = characterService.findById(id);
        currentCharacter.setQuestAgreeWitch(true);
        characterService.save(currentCharacter);
        return "redirect:/witchsLair";
    }

    @PostMapping("/getBones")
    public String getBones(@RequestParam(value = "id") Long id) {
        Character currentCharacter = characterService.findById(id);
        currentCharacter.setQuestAgreeWitch(false);
        currentCharacter.setQuestAgreeBoss(true);
        currentCharacter.getInventoryCharacter().setBone(0);
        characterService.save(currentCharacter);
        return "redirect:/witchsLair";
    }
}
