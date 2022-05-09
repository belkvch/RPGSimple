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
import java.math.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ruins")
public class RuinsController {
    private final DefaultCharacterService characterService;
    private final DefaultInventoryService inventoryService;

    @GetMapping
    public String ruinsPage(Model model) {
            User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
            model.addAttribute("character", characterUser);
            Inventory inventoryByCharacter = inventoryService.findInventoryByCharacter(characterUser.getInventoryCharacter().getId());
            model.addAttribute("inventory", inventoryByCharacter);
            return "ruins";
    }

    @PostMapping
    public String buyKey(@RequestParam(value = "id") Long id, Model model) {
        Character currentCharacter = characterService.findById(id);
        Inventory currentInventory = inventoryService.findInventoryByCharacter(currentCharacter.getInventoryCharacter().getId());
        BigDecimal changeGold = currentInventory.getGold().subtract(new BigDecimal(2000));
        if (changeGold.compareTo(BigDecimal.valueOf(0)) > 0 || changeGold.compareTo(BigDecimal.valueOf(0)) == 0) {
            currentInventory.setGold(changeGold);
            currentInventory.setKey(true);
            inventoryService.save(currentInventory);
        } else {
            model.addAttribute("goldError", "You don't have any gold");
            model.addAttribute("character", currentCharacter);
            model.addAttribute("inventory", currentInventory);
            return "ruins";
        }
        return "redirect:/ruins";
    }
}
