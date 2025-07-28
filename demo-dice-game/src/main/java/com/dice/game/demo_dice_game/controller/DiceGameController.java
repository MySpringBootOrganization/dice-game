package com.dice.game.demo_dice_game.controller;

import com.dice.game.demo_dice_game.service.PlayDiceGame;
import com.dice.game.demo_dice_game.service.SimulateDiceGame;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dice.game.demo_dice_game.model.DiceReport;

@RestController
@RequestMapping("/dice-game")
@RequiredArgsConstructor
public class DiceGameController {

  final PlayDiceGame playDiceGame;
  final SimulateDiceGame simulateDiceGame;

  @GetMapping("/play")
  public ResponseEntity<Object> playgame(
    @RequestParam Integer numOfDice,
    @RequestParam Integer targetNum
  ) {
    Map<String, int[]> memo = new HashMap<>(); // Assuming memo is a simple HashMap for this example
    int score = playDiceGame.play(memo, numOfDice, targetNum); // Assuming memo is not used in this context
    String result =
      "Playing game with " +
      numOfDice +
      " dice \n" +
      "Remove number:" +
      targetNum +
      "\n" +
      "Score: " +
      score;
    DiceReport diceReport = new DiceReport();
    diceReport.setResult(result); // Assuming you want to set the result in the DiceReport object    
    System.out.println(result); // Print the result to console for debugging  
    return ResponseEntity.ok(diceReport);

  }

  @GetMapping("/simulate")
  public ResponseEntity<Object> simulateGame(
    @RequestParam Integer numOfDice,
    @RequestParam Integer targetNum,
    @RequestParam Integer simulations
  ) {
    
    String result = simulateDiceGame.simulateGame(numOfDice, targetNum, simulations); // Assuming memo is not used in this context
    DiceReport diceReport = new DiceReport();
    diceReport.setResult(result); // Assuming you want to set the result in the DiceReport object    
    System.out.println(result); // Print the result to console for debugging  
    return ResponseEntity.ok(diceReport);
  }
}
