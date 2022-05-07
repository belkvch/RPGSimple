package com.example.rpgsimple.controller;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usePoiton")
public class PoitonController {
    private final DefaultCharacterService characterService;
    private final DefaultInventoryService inventoryService;

    @PostMapping
    public String usePoiton(@RequestParam(value = "id") Long id, @RequestParam(value = "url") String baseUrl) {

        Character currentCharacter = characterService.findById(id);
        Inventory currentInventory = inventoryService.findInventoryByCharacter(currentCharacter.getInventoryCharacter().getId());
        if (currentInventory.getHpBottle() > 0) {
            currentInventory.setHpBottle(currentInventory.getHpBottle() - 1);
            inventoryService.save(currentInventory);

            Integer hpChange = currentCharacter.getCurrentHp() + 3;
            if (hpChange > currentCharacter.getHp()) {
                currentCharacter.setCurrentHp(currentCharacter.getHp());
                characterService.save(currentCharacter);
            } else {
                currentCharacter.setCurrentHp(hpChange);
                characterService.save(currentCharacter);
            }
        }

        return "redirect:" + baseUrl;
    }
}
