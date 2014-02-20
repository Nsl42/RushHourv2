import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


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
		String niveau = numNiveau == 1 ? "1-beginner" : numNiveau == 2 ? "2-intermediate" : numNiveau == 3 ? "3-s" +
				"advanced" : numNiveau == 4 ? "4-expert" : "5-grandmaster";
		
		try{
			int i = 0;
			String ligne = "";
			int tableauScore[] = {};
			
			// Lecture du fichier du niveau concerné
			InputStream ips=new FileInputStream("niveaux/" + niveau + ".cfg"); // Le chemin relatif est buggé
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String value;
			while ((value = br.readLine())!=null){
				if(i+1 == numNiveau)
				{
					ligne = value;
				}
			}
			br.close(); 
			
			
			// Lecture du fichier véhicule
			InputStream ips2=new FileInputStream("niveaux/vehicles.dat"); 
			InputStreamReader ipsr2=new InputStreamReader(ips2);
			BufferedReader br2=new BufferedReader(ipsr2);

			
			String split[] = ligne.split(" ");
			char [] tab1;
			char [] tab2;
			
			for(int j=0; j<split.length; i++)
			{
				while ((value = br2.readLine())!=null)
				{
					tab1 = split[j].toCharArray();
					tab2 = value.toCharArray();
					if(tab1[0] == tab2[0])
					{						
						// On add la voiture
						int width = tab1[1] == 'H' ? tab2[1] : 1;
						int height = tab1[1] == 'V' ? 1 : tab2[1];
						parking.addVehicule(Character.toString(tab1[0]), width, height, Character.getNumericValue(tab1[2]), Character.getNumericValue(tab1[3]), ligne.substring(2));
					}
				}
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
