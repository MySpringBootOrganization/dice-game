package com.dice.game.demo_dice_game.service;

import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class SimulateDiceGame {

  @Autowired
  PlayDiceGame playDiceGame;

  private Map<String, int[]> memo;

  public void simulateGame(int numOfDice, int targetNum, int simulations) {
    this.memo = new HashMap<>();
    long startTime = System.currentTimeMillis();

    // Initialize score counts
    int maxPossibleScore = numOfDice * 6;
    int[] scoreCounts = new int[maxPossibleScore + 1];

    for (int i = 0; i < simulations; i++) {
      int score = playDiceGame.play(memo, numOfDice, targetNum);
      if (score >= 0 && score <= maxPossibleScore) {
        scoreCounts[score]++;
      }
    }

    long endTime = System.currentTimeMillis();
    double duration = (endTime - startTime) / 1000.0;

    // Print results
    System.out.printf(
      "Number of simulations was %d using %d dice.\n",
      simulations,
      numOfDice
    );
    for (int score = 0; score <= maxPossibleScore; score++) {
      if (scoreCounts[score] > 0) {
        double percentage = (scoreCounts[score] * 100.0) / simulations;
        System.out.printf(
          "Total %d occurs %.2f occurred %.1f times.\n",
          score,
          percentage / 100.0,
          (double) scoreCounts[score]
        );
      }
    }
    System.out.printf("Total simulation took %.1f seconds.\n", duration);
  } // Start simulation
}
