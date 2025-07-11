# dice-game

Simulation of a dice game

Two solutions are provided for the game.

<strong>Solution (1) is plain java:  </strong>

<pre>
javac DiceGameSimulator.java
java DiceGameSimulator 2 3 100
</pre>

Sample output:
<pre>
Number of simulations was 100 using 2 dice.
Total 0 occurs 0.02 occurred 2.0 times.
Total 1 occurs 0.05 occurred 5.0 times.
Total 2 occurs 0.10 occurred 10.0 times.
Total 3 occurs 0.09 occurred 9.0 times.
Total 4 occurs 0.11 occurred 11.0 times.
Total 5 occurs 0.13 occurred 13.0 times.
Total 6 occurs 0.12 occurred 12.0 times.
Total 7 occurs 0.11 occurred 11.0 times.
Total 8 occurs 0.08 occurred 8.0 times.
Total 9 occurs 0.05 occurred 5.0 times.
Total 10 occurs 0.05 occurred 5.0 times.
Total 11 occurs 0.06 occurred 6.0 times.
Total 12 occurs 0.03 occurred 3.0 times.
Total simulation took 0.0 seconds.

</pre>
<strong>Solution (2) provides 2 rest api</strong>
<pre>
one can play a game by calling:
Parameters: numOfDice - number of dice used
            targetNum - the number to be removed
- http://localhost:8080/dice-game/play?numOfDice=6&targetNum=3

Sample play game output:
</pre>
![alt text](playGame.png)

<pre>
One can do the simulation by calling:
- http://localhost:8080/dice-game/simulate?numOfDice=6&targetNum=3&simulations=10000


Sample simulation output:
</pre>
![alt text](simulation.png)


