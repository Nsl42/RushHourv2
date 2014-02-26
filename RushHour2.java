import java.util.Scanner;
import java.util.Random;

public class RushHour2 {
   static Random rd = new Random();

   public static void main(String [] args)
   { 
      Scanner sc = new Scanner(System.in);

      System.out.println("----------- RUSHHOUR ------------");
      boolean lock = false;
      // Player Name Input
      Player player;
      do
      {
	 try{
	    System.out.println(" ==> Entrez votre nom ");
	    player = new player(sc.nextLine());
	 }catch(Exception e)
	 lock = true;
      }while(lock);

      // Main Menu Input
      System.out.println("-------------------- MAIN MENU --------------------");
      System.out.println(" I . Pick a specific parking configuration");
      System.out.println(" II. Pick a level & let the computer decide the configuration");
      System.out.println(" III.Let fate decide !");
      int menuChoice=0;
      lock = false;
      do
      {
	 try{
	    System.out.println(" ==> Your choice (1, 2 or 3) ");
	    menuChoice = sc.nextInt();
	 }catch(Exception e)
	 lock = true;
      }while(lock);

      if(menuChoice > 0 && menuChoice < 3)
      {
	 // Level Input
	 System.out.println("-------------------- LEVEL MENU --------------------");
	 System.out.println(" 1. Beginner");
	 System.out.println(" 2. Intermediate");
	 System.out.println(" 3. Advanced");
	 System.out.println(" 4. Expert");
	 System.out.println(" 5. Grand-Master");
	 int levelChoice = 0;
	 lock = false;
	 do
	 {
	    try{
	       System.out.println(" ==> Your choice (1, 2, 3, 4 or 5 ) ");
	       levelChoice = sc.nextInt();
	    }catch(Exception e)
	    lock = true;
	 }while(lock);
      }else
	 levelChoice = this.randomLevel(player);

      if(menuChoice == 1)
      {
	 int nbConfig = 0;
	 try{
	    //TODO : ParkingFactory.getNbConfig()
	    nbConfig = ParkingFactory.getNbConfig(levelChoice);	
	 }catch(Exception e)
	 {
	    System.out.println("Entree utilisateur illegale detectee.");
	    System.out.println("Arret du processus...");
	    System.exit();
	 }
	 // Configuration Input
	 System.out.println("-------------------- CONF MENU --------------------");
	 for(int i = 0; i<nbConfig;i++)
	    System.out.println("Level " + (i + 1) + 
		  (player.getScore(levelChoice, i) == -1)? "[]" : "[x]");
	 int configChoice = 0;
	 lock = false;
	 do
	 {
	    try{
	       System.out.println(" ==> Your choice ? ");
	       configChoice = sc.nextInt();
	    }catch(Exception e)
	    {
	       lock = true;
	    }
	 }while(lock);
      }else
	 configChoice = rd.nextInt(nbConfig);

      // Game Initialisation
      player.init(levelChoice, configChoice);
      // Play
      player.play();
      // Victory !!
      System.out.println("-------------------- VICTORY !! --------------------");
      player.save();
      System.out.println(" Score Saved !!");
      System.out.println("Bye bye :) !!");
   }



}

