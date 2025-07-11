package com.dice.game.demo_dice_game.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ReportGenerator {

    public static String generateReport(int numOfDice, int simulations, int[] scoreCounts, double duration) {
        StringBuilder report = new StringBuilder();
        report.append("Number of simulations was ").append(simulations)
              .append(" using ").append(numOfDice).append(" dice.").append(System.lineSeparator());
        
        int maxPossibleScore = numOfDice * 6;
        for (int score = 0; score <= maxPossibleScore; score++) {
            if (scoreCounts[score] > 0) {
                double percentage = (scoreCounts[score] * 100.0) / simulations;
                report.append(String.format("Total %d occurs %.2f%% occurred %.1f times.%n", 
                                             score, percentage, (double) scoreCounts[score]));
            }
        }
        report.append(String.format("Total simulation took %.1f ms.%n", duration));
        
        return report.toString();
    }

}
