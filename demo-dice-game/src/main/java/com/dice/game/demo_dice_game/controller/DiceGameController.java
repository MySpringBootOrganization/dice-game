package com.dice.game.demo_dice_game.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dice.game.demo_dice_game.service.PlayDiceGame;
import com.dice.game.demo_dice_game.service.SimulateDiceGame;

@RestController
@RequestMapping("/dice-game")
public class DiceGameController {
    @Autowired
    PlayDiceGame playDiceGame;

    @Autowired
    SimulateDiceGame simulateDiceGame;

   @GetMapping("/play")
    public String playgame(@RequestParam Integer numOfDice, @RequestParam Integer targetNum) {
         
        Map<String, int[]> memo = new HashMap<>(); // Assuming memo is a simple HashMap for this example
        int score = playDiceGame.play(memo, numOfDice, targetNum); // Assuming memo is not used in this context
        String result = "Score: " + score;
        return "Playing game with " + numOfDice  + " dice \n" + "Remove number:" + targetNum + "\n" + "Score: " + result;
    }

    @GetMapping("/simulate")
    public String simulateGame(@RequestParam Integer numOfDice, @RequestParam Integer targetNum, @RequestParam Integer simulations) {
        simulateDiceGame.simulateGame(numOfDice, targetNum, simulations); // Assuming memo is not used in this context
        return "Simulated game with " + numOfDice + " dice \n" + "Remove number: " + targetNum + "\n";
    }


}
