import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Player {
	
	public void play(int numNiveau, int configuration, ParkingFactory parking)
	{
		System.out.println("Vous avez choisi la difficult� " + numNiveau + " et la configuration " + configuration);
		String niveau = numNiveau == 1 ? "1-beginner" : numNiveau == 2 ? "2-intermediate" : numNiveau == 3 ? "3- advanced" : numNiveau == 4 ? "4-expert" : "5-grandmaster";
		
		try{
			int i = 0;
			String ligne = "";
			int tableauScore[] = {};
			
			// Lecture du fichier du niveau concern�
			InputStream ips=new FileInputStream("niveaux/" + niveau + ".cfg"); 
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
			
			
			// Lecture du fichier v�hicule
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
						parking.getParking().addVehicule(tab1[0], width, height, tab1[2], tab1[3], ligne.substring(2));
					}
				}
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}