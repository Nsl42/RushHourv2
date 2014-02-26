import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player{
   private static final String SCORE_PATH = "saves/";

   private String name;
   private ArrayList<int[]> scores;

   /** Constructor of the Player class
    * uses the name given by the user and try to load his scores
    * from a file previously saved. 
    * @param name player's name. Use as name for the save file.
    */
   public Player(String name)
   {
      this.name = name;
      load();
   }

   /**
    * Score setter.
    * Tests the existence of a score for the level and compares
    * the two scores to actualize the score if necessary, unless
    * the newScore equals to -1. In this case do nothing.
    * @param lvl
    * @param config
    * @param newScore the score to save. 
    */
   public void setScore(int lvl, int config, int newScore)
   {
      if (newScore == -1) return; // do not save score equals to -1.

      // If the level doesn't exist , the score will be created
      if (this.getScore(lvl, config) == -1)
         this.scores.add(new int[]{lvl, config, newScore});
      // Overwise, if the new score is greater than the ancient,
      // the ancient will be erased and the new will be registered
      else
         for (int[] arr : this.scores)
            if (arr[0] == lvl && arr[1] == config && arr[3] > newScore)
            {
               this.scores.add(this.scores.indexOf(arr),
                     new int[]{lvl, config, newScore});
               break;
            }

      this.save();
   }

   /**
    * Score getter.
    * @param lvl
    * @param config
    * @return the corresponding score or -1 if it doesn't exist.
    */
   public int getScore(int lvl, int config)
   {
      for (int[] arr : this.scores)
         if (arr[0] == lvl && arr[1] == config)
            return arr[2];
      return -1;
   }


   /**
    * Score saver.
    * Save the scores of the player in his attributed file
    */
   private void save ()
   {
      BufferedWriter f;
      try {
         f = new BufferedWriter(new FileWriter(Player.SCORE_PATH + this.name));
         for (int[] arr : this.scores)
         {
            for (int i = 0, c = arr.length; i < c; ++i)
               f.write(arr[i] + " ");
            f.newLine();
         }
         f.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   /**
    * Score loader.
    * Loads the scores of the player from
    * his attributed file to the score ArrayList
    */
   private void load ()
   {
      this.scores = new ArrayList<int[]>();
      // Stop if file doesn't exist.
      if (! (new java.io.File(SCORE_PATH + this.name).isFile())) return;

      String line;
      BufferedReader f;
      try {
         f = new BufferedReader(new FileReader(Player.SCORE_PATH + this.name));
         while ((line = f.readLine()) != null)
         {
            final String[] score = line.split(" ");
            this.scores.add(new int[]{
                  Integer.parseInt(score[0]),
                  Integer.parseInt(score[1]),
                  Integer.parseInt(score[2])
            });
         }
         f.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method that executes the game itself
    * 
    */
   public int play(int levelChoice, int configChoice)
   {
      ParkingFactory pf = ParkingFactory.getParkingFactory(); 
      final Parking park = pf.getParking();

      System.out.print("Enter Your Move : ");
      final Scanner sc = new Scanner(System.in);
      int score = 0;
      while(park.getVehicule("X").getXPosition() <= 184)
      {
         if (!sc.hasNextLine())
         {
            System.out.println("exit");
            return -1;
         }
         final String move = sc.nextLine().trim().toUpperCase();
         if ("EXIT".equals(move))
         {
            System.out.println("exit");
            return -1;
         }
         try{
            park.move(move);
            ++score;
         }catch(Exception e)
         {
            System.out.println("Illegal Move !!");
         } finally {
            System.out.println("Another Move : ");
         }
      }
      return score;
   }

   /**
    * Method that returns a random level, based on player's score
    * @return int rdLevel The Random level (1..5)
    */
   public int randomLevel()
   {
      return 2;
   }
}
