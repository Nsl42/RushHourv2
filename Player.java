package rush.hour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Player {

    private String nom;
    private ArrayList score;
    
    /** Constructor of the Player class
     * uses the name given by the user to find him in the records or create a new player
     * @param name 
     */
    public Player (String name)
    {
        nom=name;
        load();
    }
    
    
    /** Score setter
     * Tests the existence of a score for the level and compares the two scores to actualize the score if necessary
     * @param complexity
     * @param configuration
     * @param score 
     */
    public void setScore (int complexity, int configuration, int newscore)
    {
        // Finds the position of the score of the level before the one you want to register in the ArrayList
        int position=-1;
        boolean exists=false;
        for (int i=0; i<score.size(); i+=3)
        {
            if (complexity>=(int)score.get(i))
            {
                if((int)score.get(i+1)<configuration)
                {
                    position=i;
                }
                if ((int)score.get(i+1)==configuration)
                {
                    exists=true;
                }
            }
        }
        // If the level doesn't exist , the score will be created
        if (!exists)
        {
            score.add(position+3,newscore);
            score.add(position+3,configuration);
            score.add(position+3,complexity);
        }
        // Overwise, if the new score is greater than the ancient, the ancient will be erased and the new will be registered
        else
        {
            if ((int)score.get(position+5)<newscore)
            {
                score.remove(position+3);
                score.remove(position+3);
                score.remove(position+3);
                score.add(position+3,newscore);
                score.add(position+3,configuration);
                score.add(position+3,complexity);
            }
        }
    }
    
    
    /** Score getter
     * Finds the score and displays it (-1 if not existing)
     * @param complexity
     * @param configuration
     * @return 
     */
    public int getScore (int complexity, int configuration)
    {
        // Finds the position of the score of the level in the ArrayList and returns the value of the score
        for (int i=0; i<score.size();i+=3)
        {
            if (complexity==(int)score.get(i))
            {
                if((int)score.get(i+1)==configuration)
                {
                    return (int)score.get(i+2);
                }
            }
        }
        return (int)score.get(-1);
    }
    
    
    /** Score saver
     * Save the scores of the player in his attributed file
     */
    public void save ()
    {
        try
        {
            File f =  new File(nom);
                // Convert the score ArrayList to a suitable String in order to write it in the player's file
                String temp = "";
                for (int i=0; i<score.size(); i+=3)
                {
                    for (int j=0; j<3; j++)
                    {
                        temp += score.get(i+j) + "-";
                    }
                    temp += " ";
                }
                // Rewrite the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                writer.write(temp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /** Score loader
     * Loads the scores of the player from his attributed file to the score ArrayList
     */
    public void load ()
    {
        try
        {
            File f =  new File(nom);
            // Tests the existence of the player's file
            if (f.exists())
            {
                //Reads the file
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String scores = reader.readLine();
                //Splits the scores string in levels
                String record[]=scores.split(" ");
                //Splits the record string array in complexity, configuration and score and puts the values int the score ArrayList
                for (int i=0; i<record.length; i++)
                {
                     String temp[]=record[i].split("-");
                     for (int j=0; j<temp.length; j++)
                     {
                        score.add(temp[j]);
                     }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /** Method that executes the game itself
     * 
     * @param parking 
     */           
    public void play(ParkingFactory parking)
    {
		
    }
}
