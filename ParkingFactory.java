import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;


public class ParkingFactory {

   private static ParkingFactory parkingFactorySingleton;

   private Parking parking;
   private ArrayList<ArrayList<Position>>[] ArrConfs;
   private ArrayList<String> ArrCars;

   public static ParkingFactory getParkingFactory()
   {
      if(parkingFactorySingleton == null)
	 parkingFactorySingleton = new ParkingFactory();

      return parkingFactorySingleton;
   }



   private ParkingFactory()
   {

      loadfiles();
   }

   public Parking createPark(int levelChoice, int configChoice)
   {
      Parking park = new Parking();
      ArrayList<Position> selectedConfig = ArrConfs[levelChoice-1].get(configChoice - 1);
      for(Position pos: selectedConfig)
      {
	 String carData = "";
	 for(String data: ArrCars)
	    if (pos.car.equals(data.substring(0,1)))
	       carData = data;

	 park.addVehicule(pos.car, 
	       (pos.direction.equals(Constants.HORIZONTAL))? Constants.SQUARE * Integer.parseInt(carData.substring(2,3)) : Constants.SQUARE,
	       (pos.direction.equals(Constants.VERTICAL))? Constants.SQUARE * Integer.parseInt(carData.substring(2,3)) : Constants.SQUARE,
	       pos.posX, pos.posY, carData.substring(3));
      }
	 return park;
   }


   public Parking getParking()
   {
      return parking;
   }

   private void loadfiles()
   {
      this.ArrConfs = loadConfs();
      this.ArrCars = loadCars();
   }


   static private ArrayList<ArrayList<Position>>[] loadConfs()
   {
      String niveau = "";

      ArrayList<ArrayList<Position>> lvl[] = (ArrayList<ArrayList<Position>>[])new ArrayList[5];

      for(int i = 1; i < 6; i++)
      {
	 lvl[i-1] = new ArrayList<ArrayList<Position>>();
	 niveau = ((i == 1) ? "1-beginner" : 
	       		((i == 2) ? "2-intermediate" : 
			 	((i == 3) ? "3-advanced" : 
				 	((i == 4) ? "4-expert" : "5-grandmaster"))));
	 //debug
	 System.out.println("LECTURE");	
	 System.out.println("niveaux/" + niveau + ".cfg");
	 String line;
	 // Lecture du fichier du niveau concerné
	 try{
	 BufferedReader nivbr = new BufferedReader(new FileReader("niveaux/" + niveau + ".cfg"));
	 while((line = nivbr.readLine()) != null)
	 {
	    ArrayList<Position> confs = new ArrayList<Position>();
	    String temp_line[] = line.split(" ");
	    for(int j = 1; j < temp_line.length; j++)
	    {
	       Position pos = new Position(temp_line[i]);
	       confs.add(pos);
	    }
	    lvl[i-1].add(confs);

	 }
	 nivbr.close(); 
	 }catch(Exception e)
	 {
	    System.out.println("Erreur de lecture des fichiers de configuation...");
	    e.printStackTrace();
	    System.out.println("Arrêt du programme...");
	    System.exit(1);
	  }
      }
      return lvl;
   }

   static public  ArrayList<String> loadCars()
   {
      String line = "";
      ArrayList<String> lines = new ArrayList<String>();
      try {
      BufferedReader vehibr = new BufferedReader(new FileReader("niveaux/vehicles.dat"));
	 line = vehibr.readLine();
	 while(line != null)
	 {  lines.add(line);
	    //debug
	    System.out.println(line);
	    line = vehibr.readLine();
	 }
	 }catch(Exception e)
	 {
	    System.out.println("Erreur de lecture des fichiers de configuation...");
	    System.out.println("Arrêt du programme...");
	    System.exit(1);
	  }
      return lines;
   }

   /** Method that returns the number of available configs, given a level number
    * @param int lvlNumber 
    * @return int nbConf
    */
   public int getNbConfig(int lvlNumber)
   {
      return this.ArrConfs[lvlNumber - 1].size();
   }
}

