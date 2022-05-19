package com.company;

import java.util.Date;
import java.util.Scanner;



public class Main {

    public static void main(String[] args)
    {
        PreviousGameResult pgr = new PreviousGameResult(new Date());
        while(true) {
            System.out.println("\n*********************************\n"
                    + "Welcome to the word guessing game\n"
                    + "Please input the number corresponding to the option you want to select\n"
                    + "1: Start Game\n"
                    + "2: Result of previous game\n"
                    + "3: Exit\n");
            Scanner sc = new Scanner(System.in);
            int userChoice;
            try
            {
                userChoice=sc.nextInt();
            }
            catch (Exception e)
            {
                System.out.println("Please enter valid input");
                continue;
            }

            switch(userChoice)
            {

                case (2):
                {
                    pgr.getData();
                    break;
                }
                case (3):
                {
                    System.out.println("The End!");
                    return;
                }
                case (1):
                {

                    pgr.setPoints(0);

                    boolean gameLost = false;
                    Level level = new Level(1);

                    while(level.getLevelNumber()<4 ) {
                        while(level.getChancesRemaining()>0) {
                            System.out.println(level.toString());
                            System.out.println("Enter character: ");
                            char s = sc.next().charAt(0);
                            //s = sc.nextLine();//SEND CHAR OVER HERE IN LOWER CASE
                            s = Character.toLowerCase(s);
                            System.out.println("You guessed : " + s);
                            level.checkGuess(s);
                            //System.out.println(level);
                            if (!level.word.hasLettersRemaining()) {
                                System.out.println("You win level " + level.getLevelNumber() + "\n-----------------------\n");
                                pgr.setPoints(pgr.getPoints() + level.getChancesRemaining());

                                if(level.getLevelNumber() == 3){
                                    System.out.println("YOU HAVE WON THE GAME!! \n Your score is:" + pgr.getPoints());
                                    pgr.saveDate();
                                    break;
                                }
                                break;
                            }
                            else if (level.getChancesRemaining() < 1) {
                                System.out.println("You lose level " + level.getLevelNumber() + "\nThe word was: " + level.getWord().getActualWord() + "\n----------\n");
                                pgr.saveDate();
                                gameLost = true;
                                break;
                            }
                        }
                        if(!gameLost)
                            level = new Level((level.getLevelNumber())+1);
                        else{
                            pgr.setPoints(0);
                            break;
                        }
                    }
                    break;
                }
                default:
                {
                    System.out.println("Enter Valid option");
                }
            }
        }
    }

}


