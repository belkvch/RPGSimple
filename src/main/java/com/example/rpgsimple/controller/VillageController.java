package com.example.rpgsimple.controller;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/village")
public class VillageController {
    private final DefaultCharacterService characterService;
    private final DefaultInventoryService inventoryService;


    @GetMapping
    public String villagePage(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
        model.addAttribute("character", characterUser);
        Inventory inventoryByCharacter = inventoryService.findInventoryByCharacter(characterUser.getInventoryCharacter().getId());
        model.addAttribute("inventory", inventoryByCharacter);
        return "village";
    }

    @PostMapping
    public String buyPotion(@RequestParam(value = "id") Long id, Model model) {
        Character currentCharacter = characterService.findById(id);
        Inventory currentInventory = inventoryService.findInventoryByCharacter(currentCharacter.getInventoryCharacter().getId());
        BigDecimal changeGold = currentInventory.getGold().subtract(new BigDecimal(500));
        if (changeGold.compareTo(BigDecimal.valueOf(0)) > 0 || changeGold.compareTo(BigDecimal.valueOf(0)) == 0) {
            currentInventory.setGold(changeGold);
            currentInventory.setHpBottle(currentInventory.getHpBottle() + 1);
            inventoryService.save(currentInventory);
        } else {
            model.addAttribute("GoldError", "You haven't gold");
        }
        return "redirect:/village";
    }
}
