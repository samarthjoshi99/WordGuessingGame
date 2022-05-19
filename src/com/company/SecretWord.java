package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecretWord
{
    private String actualWord; //Stores the word user has to guess
    private StringBuilder currentWord; //Stores the word in underscore
    private static List<String> wordList = new ArrayList<>(); //Contains words from file that will be picked randomly

    SecretWord(int level){
        if(level == 1)
            readWordsFromFile();//Read words from file when playing from level 1
        this.actualWord = getRandomWordFromFile();
        currentWord = new StringBuilder();
        for(int i =0; i<this.actualWord.length(); i++) {
            currentWord.append("_");
        }


    }

    public void readWordsFromFile(){
        try {
            // create obj of file reader and return file in read mode
            FileReader file = new FileReader("/Users/urja/IdeaProjects/project/src/com/company/name.txt");
            // create BufferedReader and pass object of file we want to  read
            try (BufferedReader br = new BufferedReader(file)) {
                String line;
                //readline reads line from BufferedReader until its not empty
                while ((line = br.readLine()) != null) {
                    //add string to wordlist
                    wordList.add(line);

                }
            } catch (IOException e) {
                System.out.println("File Not found!");
                return;
            }
        }
        //throw error if file doesn't exist
        catch (IOException e) {
            System.out.println("File Not found!");
            return;
        }
    }

    public String getRandomWordFromFile(){
        //if no word is available
        if(wordList.size() == 0){
            return "No more words left";
        }
        Random random = new Random(); //create object of random
        int randomItem = random.nextInt(wordList.size());  // get random location from wordlist
        String randomWord = wordList.get(randomItem); // get word from index
        wordList.remove(randomItem); // remove it from arraylist


        return randomWord; // return string
    }

    protected String getActualWord()
    {
        return actualWord;
    }

    protected StringBuilder getCurrentWord()
    {
        return currentWord;
    }

    protected void setCurrentWord(StringBuilder updatedCurrentWord) {
        this.currentWord = updatedCurrentWord;
    }

    public boolean containsLetter(char letter)
    {
        return this.actualWord.toLowerCase().contains(Character.toString(letter)); // return true if guessed letter
    }

    public boolean hasLettersRemaining()
    {
        if(this.currentWord.toString().contains("_")) return true; // return true if dash is present
        return false;
    }

    public String toString()
    {
        return this.currentWord.toString();
    }
}
