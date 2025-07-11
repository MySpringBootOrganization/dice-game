package com.dice.game.demo_dice_game.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dice.game.demo_dice_game.utility.ReportGenerator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SimulateDiceGame {

  final PlayDiceGame playDiceGame;

  private Map<String, int[]> memo;

  public String simulateGame(int numOfDice, int targetNum, int simulations) {
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
    double duration = (endTime - startTime);

    return ReportGenerator.generateReport(
      numOfDice,
      simulations,
      scoreCounts,
      duration
    );
  } // Start simulation
}
