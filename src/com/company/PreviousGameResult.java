package com.company;
import java.io.*;
import java.util.Date;
// Import the Scanner class to read text files


public class PreviousGameResult {
    private Date datePlayed;
    private static int points = 0;

    //Save the data to file
    public void saveDate()
    {
        try {
            //Path of file in which we want to add data
            File file = new File("/Users/urja/IdeaProjects/project/src/com/company/previousFile.txt");
            FileWriter fw = new FileWriter(file, true); // create FileWriter obj and append true becuse we want to add data to end of file
            fw.write("Total Points: " + this.getPoints() + " Date And Time Of Game Played: " + this.getDatePlayed() + "\n"); // write is used to write char or string in file
            fw.close(); // close file
        } catch (IOException e) {
            e.printStackTrace(); //print if any error(file not exist or not a regular file)
        }
    }
    public void getData()
    {
        try {

            FileReader file = new FileReader("/Users/urja/IdeaProjects/project/src/com/company/previousFile.txt");
            try (BufferedReader br = new BufferedReader(file)) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(line.isEmpty() == true)
                    {
                        System.out.print("not found");
                    }
                   System.out.println(line);
                }

            } catch (IOException e) {
                System.out.println("File Not found!");

            }
        }
        catch (IOException e) {
            System.out.println("File Not found!");
        }
    }

    public PreviousGameResult(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString()
    {
        return "\n Date of previous game was: " + datePlayed;
    }
}
