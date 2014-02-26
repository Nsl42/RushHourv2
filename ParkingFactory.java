import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;


public class ParkingFactory {
   private Parking parking;

   public ParkingFactory(int numNiveau, int configuration)
   {
      parking = new Parking(); 
      chargerFichier(numNiveau, configuration);
   }

   public Parking getParking()
   {
      return parking;
   }

   public void chargerFichier(int numNiveau, int configuration)
   {
      System.out.println("Vous avez choisi la difficulté " + numNiveau + " et la configuration " + configuration);
      String niveau = ((numNiveau == 1) ? "1-beginner" : ((numNiveau == 2) ? "2-intermediate" : ((numNiveau == 3) ? "3-" +
		  "advanced" : ((numNiveau == 4) ? "4-expert" : "5-grandmaster"))));
      try{
	 int i;
	 int tableauScore[] = {};
	 //debug
	 System.out.println("LECTURE");	
	 System.out.println("niveaux/" + niveau + ".cfg");
	 String line;
	 // Lecture du fichier du niveau concerné
	 BufferedReader nivbr = new BufferedReader(new FileReader("niveaux/" + niveau + ".cfg"));
	 try {
	    line = nivbr.readLine();
	    for(int j = 0; j<numNiveau-1;j++)
	       nivbr.readLine();
	 }catch(Exception e)
	 {
	    e.printStackTrace();
	 }
	 String config = nivbr.readLine();

	 // ---------------------------------------------------------------------------------------------
	 /*
	    InputStream ips=new FileInputStream("niveaux/" + niveau + ".cfg"); // Le chemin relatif est buggé
	    InputStreamReader ipsr=new InputStreamReader(ips);
	    BufferedReader nivbr=new BufferedReader(ipsr);
	    String value;
	    while ((value = nivbr.readLine())!=null){
	    if(i+1 == numNiveau)
	    {
	    ligne = value;
	    }
	    }*/
	 nivbr.close(); 

	 //debug
	 System.out.println("toto");
	 BufferedReader vehibr = new BufferedReader(new FileReader("niveaux/vehicles.dat"));
	 ArrayList<String> lines = new ArrayList<String>();
	 try {
	    line = vehibr.readLine();
	    while(line != null)
	    {  lines.add(line);
	       //debug
	       System.out.println(line);
	       line = vehibr.readLine();
	    }
	 }catch(Exception e)
	 {
	    e.printStackTrace();
	 }
	//debugu
	System.out.println("Parsing the config...");
	 // Parsing the config 
	 String configArr[] = config.split(" ");

	System.out.println("ForEach Line...");
	 // Parsing the config 
	 // For each Line of the Vehicules config
	 for(String myline : lines)
	 {
	    for(i = 0; i < configArr.length; i++)
	    {
	       //debug
	       System.out.println(configArr[i].substring(0,1) + " .. " + myline.substring(0,1));
	       if(configArr[i].substring(0,1).equals(myline.substring(0,1)))
	       {						
		  // On add la voiture
		  int width = configArr[i].substring(1,2).equals(Constants.HORIZONTAL) ? Integer.parseInt(myline.substring(1,2)) : 1;
		  int height = configArr[i].substring(1,2).equals(Constants.VERTICAL) ? Integer.parseInt(myline.substring(1,2)) : 1;
		//debug
		System.out.println(configArr[i].substring(0,1));
		  parking.addVehicule(configArr[i].substring(0,1), width, height, Integer.parseInt(configArr[i].substring(2,3)),Integer.parseInt(configArr[i].substring(3,4)), myline.substring(2));
	       }
	    }
	 }
      }		
      catch (Exception e){
	 System.out.println(e.getCause().toString());
      }
   }
   /** Method that returns the number of available configs, given a level number
    * @param int lvlNumber 
    * @return int nbConf
    */
   static public int getNbConfig(int lvlNumber)
   {
      return 4;
   }
}
