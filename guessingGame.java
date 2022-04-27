import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
/*

A number-guessing game.

*/
public class guessingGame {
    static int readInt(String prompt) throws IOException {
        int result = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        while (true) {
            try {
                result = Integer.parseInt(reader.readLine());
                return result;
            }
            catch(NumberFormatException e) {
                System.out.print("You silly Goose you can't enter that! re-enter a NUMBER: ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in your name to begin the game: ");
        String username = input.nextLine();
        System.out.println("Enter a low range this game");
        int lowEnd = input.nextInt();
        System.out.println("Enter a high range this game");
        int highEnd = input.nextInt();
        System.out.println(username + ", I'm thinking of a number between " + lowEnd + " and " + highEnd + "." +
                "\nTry and guess my number!");
        Random answer = new Random(); //Random(100) just picks a specific seed
        int finalAnswer = ThreadLocalRandom.current().nextInt(lowEnd, highEnd);
        //System.out.println(finalAnswer);
        int userGuesses = 0;
        int leastGuesses = 0;
        int limitGuesses = 15;

        for(;;) {

            System.out.println("Please guess the number I'm thinking now:");

            int guess = readInt("Enter a number now, hit -1 to quit: ");

            if(guess == -1) {
                System.out.println("Nice seeing you! Now Goodbye");
                break;
            }
            if (limitGuesses == userGuesses) {
                System.out.println("That is guess #"+ limitGuesses + "That's too many guesses!" +
                        " Game over! For the purposes of brevity I will be letting choose a number" +
                        " between 1 and 100 from now on");
                userGuesses = 0;
                finalAnswer = (int)Math.abs(answer.nextInt()%100);
                continue;
            }
            if(guess == finalAnswer){
                userGuesses++;
                System.out.println("You win! " + finalAnswer + " was the number I was guessing!");
                System.out.println("Well done, " + username + "! You found my number in " + userGuesses + " tries!");
                if(userGuesses < leastGuesses) {
                    System.out.println(userGuesses + " is the current high score!");
                    leastGuesses = userGuesses;
                } else {
                    System.out.println(userGuesses + " is the current high score! Congrats on your new high score!");
                }
                System.out.println("Game over! For the purposes of brevity I will be letting choose a number" +
                " between 1 and 100 from now on");
                userGuesses = 0;
                finalAnswer = (int)Math.abs(answer.nextInt()%100);
                continue;
            }
            if( guess > finalAnswer) {
                System.out.println("Sorry, My number is SMALLER than your guess");
                userGuesses++;

            }
            if(guess < finalAnswer) {
                System.out.println("Sorry, My number is LARGER than your guess");
                userGuesses++;
            }
        }
    }
}
