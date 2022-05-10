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
@RequestMapping("/fight")
public class FightController {
    private final DefaultCharacterService characterService;
    private final DefaultEnemyService enemyService;

    @GetMapping
    public String fightPage(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
        model.addAttribute("character", characterUser);

        int randomNumber = (int) ((Math.random() * 4) + 1);
        Enemy currentEnemy;
        if (randomNumber == 4) {
            currentEnemy = enemyService.findById(2L);
        } else {
            currentEnemy = enemyService.findById(1L);
        }
        model.addAttribute("url", "/ruins");
        model.addAttribute("enemy", currentEnemy);
        return "fight";
    }

    @GetMapping("/cave")
    public String fightPageCave(Model model) {
        User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Character characterUser = characterService.findUserByIdUser(userCurrent.getId());
        model.addAttribute("character", characterUser);

        int randomNumber = (int) ((Math.random() * 2) + 1);
        Enemy currentEnemy;
        if (randomNumber == 2) {
            currentEnemy = enemyService.findById(2L);
        } else {
            currentEnemy = enemyService.findById(1L);
        }
        model.addAttribute("url", "/cave");
        model.addAttribute("enemy", currentEnemy);
        return "fight";
    }

    @PostMapping("/attack")
    public String attack(Model model, @RequestParam(value = "id") Long id, @RequestParam(value = "idEnemy") Long idEnemy, @RequestParam(value = "url") String url) {
        Character currentCharacter = characterService.findById(id);
        Enemy currentEnemy = enemyService.findById(idEnemy);
        int randomNumber = (int) ((Math.random() * 2) + 1);
        if (randomNumber == 1) {

            if (currentCharacter.getSpeed() > currentEnemy.getSpeed()) {
                currentEnemy.setCurrentHp(currentEnemy.getCurrentHp() - currentCharacter.getAttack());
                enemyService.save(currentEnemy);
                if (currentEnemy.getCurrentHp() > 0) {
                    currentCharacter.setCurrentHp(currentCharacter.getCurrentHp() - currentEnemy.getAttack());
                    characterService.save(currentCharacter);
                    if (currentCharacter.getCurrentHp() > 0) {
                        model.addAttribute("character", currentCharacter);
                        model.addAttribute("enemy", currentEnemy);
                        model.addAttribute("yourTurn", "You attacked");
                        model.addAttribute("enemyTurn", "Enemy attacked");
                        return "fight";
                    } else {
                        currentCharacter.setCurrentHp(currentCharacter.getHp());
                        characterService.save(currentCharacter);
                        currentEnemy.setCurrentHp(currentEnemy.getHp());
                        enemyService.save(currentEnemy);
                        return "redirect:/death";
                    }
                } else {
                    currentEnemy.setCurrentHp(currentEnemy.getHp());
                    enemyService.save(currentEnemy);

                    currentCharacter.getInventoryCharacter().setGold(currentCharacter.getInventoryCharacter().getGold().add(currentEnemy.getGold()));
                    currentCharacter.setCurrentExperience(currentCharacter.getCurrentExperience() + currentEnemy.getExp());
                    characterService.save(currentCharacter);
                    if (currentCharacter.getCurrentExperience() >= currentCharacter.getScoreExpToNextLvl()) {
                        currentCharacter.setCurrentExperience(currentCharacter.getCurrentExperience() - currentCharacter.getScoreExpToNextLvl());
                        currentCharacter.setScoreExpToNextLvl(currentCharacter.getScoreExpToNextLvl() + 15);
                        currentCharacter.setLvl(currentCharacter.getLvl() + 1);
                        currentCharacter.setAttack(currentCharacter.getAttack() + currentCharacter.getClassCharacter().getPriorityAttack());
                        currentCharacter.setHp(currentCharacter.getHp() + currentCharacter.getClassCharacter().getPriorityHP());
                        currentCharacter.setSpeed(currentCharacter.getSpeed() + currentCharacter.getClassCharacter().getPrioritySpeed());
                        characterService.save(currentCharacter);
                    }
                    return "redirect:" + url;
                }
            } else {
                currentCharacter.setCurrentHp(currentCharacter.getCurrentHp() - currentEnemy.getAttack());
                characterService.save(currentCharacter);
                if (currentCharacter.getCurrentHp() > 0) {
                    currentEnemy.setCurrentHp(currentEnemy.getCurrentHp() - currentCharacter.getAttack());
                    enemyService.save(currentEnemy);
                    if (currentEnemy.getCurrentHp() > 0) {
                        model.addAttribute("yourTurn", "You attacked");
                        model.addAttribute("enemyTurn", "Enemy attacked");
                        model.addAttribute("character", currentCharacter);
                        model.addAttribute("enemy", currentEnemy);
                        return "fight";
                    } else {
                        currentEnemy.setCurrentHp(currentEnemy.getHp());
                        enemyService.save(currentEnemy);

                        currentCharacter.getInventoryCharacter().setGold(currentCharacter.getInventoryCharacter().getGold().add(currentEnemy.getGold()));
                        currentCharacter.setCurrentExperience(currentCharacter.getCurrentExperience() + currentEnemy.getExp());
                        characterService.save(currentCharacter);
                        if (currentCharacter.getCurrentExperience() >= currentCharacter.getScoreExpToNextLvl()) {
                            currentCharacter.setCurrentExperience(currentCharacter.getCurrentExperience() - currentCharacter.getScoreExpToNextLvl());
                            currentCharacter.setScoreExpToNextLvl(currentCharacter.getScoreExpToNextLvl() + 15);
                            currentCharacter.setLvl(currentCharacter.getLvl() + 1);
                            currentCharacter.setAttack(currentCharacter.getAttack() + currentCharacter.getClassCharacter().getPriorityAttack());
                            currentCharacter.setHp(currentCharacter.getHp() + currentCharacter.getClassCharacter().getPriorityHP());
                            currentCharacter.setSpeed(currentCharacter.getSpeed() + currentCharacter.getClassCharacter().getPrioritySpeed());
                            characterService.save(currentCharacter);
                        }
                        return "redirect:" + url;
                    }
                } else {
                    currentCharacter.setCurrentHp(currentCharacter.getHp());
                    characterService.save(currentCharacter);
                    currentEnemy.setCurrentHp(currentEnemy.getHp());
                    enemyService.save(currentEnemy);
                    return "redirect:/death";
                }
            }
        } else {
            model.addAttribute("character", currentCharacter);
            model.addAttribute("enemy", currentEnemy);
            model.addAttribute("yourTurn", "You attacked");
            model.addAttribute("enemyTurn", "Enemy defended");
            return "fight";
        }
    }

    @PostMapping("/defend")
    public String defend(Model model, @RequestParam(value = "id") Long id, @RequestParam(value = "idEnemy") Long idEnemy) {
        Character currentCharacter = characterService.findById(id);
        Enemy currentEnemy = enemyService.findById(idEnemy);
        int randomNumber = (int) ((Math.random() * 2) + 1);
        model.addAttribute("character", currentCharacter);
        model.addAttribute("enemy", currentEnemy);
        model.addAttribute("yourTurn", "You defended");
        if (randomNumber == 1) {
            model.addAttribute("enemyTurn", "Enemy attacked");
        } else {
            model.addAttribute("enemyTurn", "Enemy defended");
        }
        return "fight";
    }

    @PostMapping("/run")
    public String runAway(Model model, @RequestParam(value = "id") Long id, @RequestParam(value = "idEnemy") Long idEnemy) {
        Character currentCharacter = characterService.findById(id);
        Enemy currentEnemy = enemyService.findById(idEnemy);
        model.addAttribute("character", currentCharacter);
        model.addAttribute("enemy", currentEnemy);
        model.addAttribute("yourTurn", "You defended");
        if (currentCharacter.getClassCharacter().getId() == 2) {
            int randomNumberToRunBandit = (int) ((Math.random() * 2) + 1);
            if (randomNumberToRunBandit == 1) {
                return "redirect:/village";
            } else {
                int randomNumber = (int) ((Math.random() * 2) + 1);
                if (randomNumber == 1) {
                    currentCharacter.setCurrentHp(currentCharacter.getCurrentHp() - currentEnemy.getAttack());
                    characterService.save(currentCharacter);
                    if (currentCharacter.getCurrentHp() > 0) {
                        model.addAttribute("yourTurn", "You tried to escape");
                        model.addAttribute("enemyTurn", "Enemy attacked");
                        model.addAttribute("character", currentCharacter);
                        model.addAttribute("enemy", currentEnemy);
                        return "fight";
                    } else {
                        currentCharacter.setCurrentHp(currentCharacter.getHp());
                        characterService.save(currentCharacter);
                        currentEnemy.setCurrentHp(currentEnemy.getHp());
                        enemyService.save(currentEnemy);
                        return "redirect:/death";
                    }
                } else {
                    model.addAttribute("yourTurn", "You tried to escape");
                    model.addAttribute("enemyTurn", "Enemy defended");
                    model.addAttribute("character", currentCharacter);
                    model.addAttribute("enemy", currentEnemy);
                    return "fight";
                }
            }

        } else {
            int randomNumberToRun = (int) ((Math.random() * 4) + 1);
            if (randomNumberToRun == 1) {
                return "redirect:/village";
            } else {
                int randomNumber = (int) ((Math.random() * 2) + 1);
                if (randomNumber == 1) {
                    currentCharacter.setCurrentHp(currentCharacter.getCurrentHp() - currentEnemy.getAttack());
                    characterService.save(currentCharacter);
                    if (currentCharacter.getCurrentHp() > 0) {
                        model.addAttribute("yourTurn", "You tried to escape");
                        model.addAttribute("enemyTurn", "Enemy attacked");
                        model.addAttribute("character", currentCharacter);
                        model.addAttribute("enemy", currentEnemy);
                        return "fight";
                    } else {
                        currentCharacter.setCurrentHp(currentCharacter.getHp());
                        characterService.save(currentCharacter);
                        currentEnemy.setCurrentHp(currentEnemy.getHp());
                        enemyService.save(currentEnemy);
                        return "redirect:/death";
                    }
                } else {
                    model.addAttribute("yourTurn", "You tried to escape");
                    model.addAttribute("enemyTurn", "Enemy defended");
                    model.addAttribute("character", currentCharacter);
                    model.addAttribute("enemy", currentEnemy);
                    return "fight";
                }
            }
        }
    }
}