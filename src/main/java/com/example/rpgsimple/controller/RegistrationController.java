package com.example.rpgsimple.controller;

import com.example.rpgsimple.entity.*;
import com.example.rpgsimple.entity.Character;
import com.example.rpgsimple.service.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.math.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final DefaultCharacterService characterService;
    private final DefaultClassService classService;
    private final DefaultInventoryService inventoryService;


    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords don't match");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "A user with that name already exists");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/create-character")
    public String createCharacterForm(Model model) {
        model.addAttribute("classes", classService.findAll());
        model.addAttribute("character", new Character());
        return "create-character";
    }

    @PostMapping("/create-character")
    public String createCharacter(@ModelAttribute Character character, Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory newInventory = new Inventory(new BigDecimal(1000), 5);
        Character characterCheck = characterService.findUserByIdUser(userCurrent.getId());
        if (characterCheck == null) {
            inventoryService.save(newInventory);
            character.setInventoryCharacter(newInventory);
            character.setUserChatacter(userCurrent);
            model.addAttribute("character", characterService.save(character));
            return "redirect:/village";
        } else {
            return "redirect:/welcomePage/authenticated";
        }
    }
}



