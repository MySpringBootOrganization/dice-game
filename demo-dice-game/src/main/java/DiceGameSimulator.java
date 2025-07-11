import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DiceGameSimulator {
    private int numOfDice;
    private int targetDiceNum;
    private int simulations;
    private Map<String, int[]> memo;
    private Random random;

    public DiceGameSimulator(int numOfDice, int targetDiceNum, int simulations) {
        this.numOfDice = numOfDice;
        this.targetDiceNum = targetDiceNum;
        this.simulations = simulations;
        this.memo = new HashMap<>();
        this.random = new Random();
    }

    public void simulate() {
        long startTime = System.currentTimeMillis();
        
        // Initialize score counts
        int maxPossibleScore = numOfDice * 6;
        int[] scoreCounts = new int[maxPossibleScore + 1];
        
        for (int i = 0; i < simulations; i++) {
            int score = playGame(numOfDice);
            if (score >= 0 && score <= maxPossibleScore) {
                scoreCounts[score]++;
            }
        }
        
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        
        // Print results
        System.out.printf("Number of simulations was %d using %d dice.\n", simulations, numOfDice);
        for (int score = 0; score <= maxPossibleScore; score++) {
            if (scoreCounts[score] > 0) {
                double percentage = (scoreCounts[score] * 100.0) / simulations;
                System.out.printf("Total %d occurs %.2f occurred %.1f times.\n", 
                                score, percentage / 100.0, (double)scoreCounts[score]);
            }
        }
        System.out.printf("Total simulation took %.1f ms.\n", duration);
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
        memo.put(key, new int[]{score});
        return score;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DiceGameSimulator <numOfDice> <targetDiceNum> <simulations>");
            return;
        }
        
        int numOfDice = Integer.parseInt(args[0]);
        int targetDiceNum = Integer.parseInt(args[1]);
        int simulations = Integer.parseInt(args[2]);
        
        DiceGameSimulator simulator = new DiceGameSimulator(numOfDice, targetDiceNum, simulations);
        simulator.simulate();
        //playGame(numOfDice);
    }
}