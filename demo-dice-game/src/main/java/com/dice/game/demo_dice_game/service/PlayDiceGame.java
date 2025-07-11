package com.dice.game.demo_dice_game.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PlayDiceGame {

  private Integer targetDiceNum;

  private Map<String, int[]> memo;
  private Random random = new Random();

  public int play(
    Map<String, int[]> memo,
    @NonNull Integer numOfDice,
    @NonNull Integer targetDiceNum
  ) {
    this.memo = memo;
    this.targetDiceNum = targetDiceNum;
    return playGame(numOfDice);
  }

  private int playGame(int diceLeft) {
    if (diceLeft == 0) {
      return 0;
    }

    // Generate a random dice roll
    int[] dice = new int[diceLeft];
    for (int i = 0; i < diceLeft; i++) {
      dice[i] = random.nextInt(6) + 1;
    }

    return calculateScore(dice);
  }

  private int calculateScore(int[] dice) {
    Arrays.sort(dice);
    String key = Arrays.toString(dice);

    // Check memoization cache
    if (memo.containsKey(key)) {
      return memo.get(key)[0];
    }

    int targetCount = 0;
    for (int num : dice) {
      if (num == targetDiceNum) {
        targetCount++;
      }
    }

    int score;
    if (targetCount > 0) {
      // Remove all target numbers
      int[] remaining = new int[dice.length - targetCount];
      int index = 0;
      for (int num : dice) {
        if (num != targetDiceNum) {
          remaining[index++] = num;
        }
      }
      score = 0 + calculateScore(remaining);
    } else {
      // Remove the lowest die
      if (dice.length > 0) {
        int lowest = dice[0];
        int[] remaining = new int[dice.length - 1];
        System.arraycopy(dice, 1, remaining, 0, remaining.length);
        score = lowest + calculateScore(remaining);
      } else {
        score = 0; // No dice left
      }
    }

    // Memoize the result
    memo.put(key, new int[] { score });
    return score;
  }
}
