package com.company;


import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Level{
    private static int levelNumber = 1;
    private int chancesRemaining=7;
    public static SecretWord word;
    private boolean debugOn=false;

    private HashSet<Character> guessChars = new HashSet<>();
    private HashMap<Character, List<Integer>> charSet = new HashMap<>();
    //This will store keys of characters in the actual word
    //The List will store the indices of those characters

    Level(int level) {
        this.word = new SecretWord(level);
        levelNumber = level;
        generateCharacterSet();
    }

    public int getChancesRemaining() {
        return chancesRemaining;
    }

    public SecretWord getWord(){
        return word;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) { this.levelNumber=levelNumber; }

    public String toString()
    {
        if(this.debugOn)
            return "Level: " + levelNumber + "\n" +"Chances Remaining: "+this.chancesRemaining +  "\n" + "Secret Word: " + this.word.getCurrentWord() + "\n" + "Actual Word" + this.word.getActualWord();
        else
            return "Level: " + levelNumber +"\n" + "Chances Remaining: " + this.chancesRemaining + "\n" + "Secret Word: " + this.word.getCurrentWord();
    }

    boolean checkGuess(char guessedLetter)
    {
        if(guessChars.contains(guessedLetter)){
            System.out.println("You have already guessed this letter");
            return false;
        }
        if(this.word.containsLetter(guessedLetter)) {
            StringBuilder sb = this.word.getCurrentWord();
            List<Integer> list = charSet.get(guessedLetter);
            for(int x : list) {
                sb.setCharAt(x, guessedLetter);
            }
            this.word.setCurrentWord(sb);
            guessChars.add(guessedLetter);
            System.out.println("Correct guess.");
            return true;
        }
        this.chancesRemaining--;
        System.out.println("Wrong guess.");
        return false;
    }

    public void generateCharacterSet() {
        String word = this.word.getActualWord();
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.toLowerCase().charAt(i);
            List<Integer> list = charSet.getOrDefault(c, new ArrayList<>()); //holds list of indices where a character is present
            list.add(i); //add the new index to the list
            charSet.put(c, charSet.getOrDefault(c, list)); //map the character with their list of indices
        }
    }

    boolean isWordGuessed()
    {
        if(word.getCurrentWord().toString().contains("_")) {
            return false;
        }
        return true;
    }
}
