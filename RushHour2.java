import java.util.Scanner;

public class RushHour2 {
	
	public static void main(String [] args)
    { 
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez votre nom : ");
		String pseudo = sc.nextLine();

		
	// MENU
		int reponseMenu = 0;
		int reponseNiveau = 0;
		int reponseConfiguration = 0;
		int tableauNbConfiguration[] = {11, 6, 7, 11, 11, 9, 14, 12, 12}; 
		
		
		// Choix du menu
		do
		{
			System.out.println("Choisissez un menu : ");
			System.out.println("1. Choisir un niveaux et un numero de configuration");
			System.out.println("2. Choisir seulement le niveau");
			System.out.println("3. Niveau aleatoire");
			reponseMenu = sc.nextInt();			
		}while(reponseMenu != 1 && reponseMenu != 2 && reponseMenu != 3);
		
		
		// Choix du niveau
		if(reponseMenu == 1 || reponseMenu == 2)
		{
			do
			{
				System.out.println("Choisissez un niveau : ");
				System.out.println("1. beginner");
				System.out.println("2. intermediate");
				System.out.println("3. advanced");
				System.out.println("4. expert");
				System.out.println("5. grandmaster");
				reponseNiveau = sc.nextInt();
			}while(reponseNiveau != 1 && reponseNiveau != 2 && reponseNiveau != 3 && reponseNiveau != 4 && reponseNiveau != 5);
		}
		
		// Choix de la configuration
		if(reponseMenu == 1)
		{
			do
			{
				System.out.println("choisissez une configuration parmis " + tableauNbConfiguration[reponseNiveau] + " :");
				reponseConfiguration = sc.nextInt();
			}while(reponseConfiguration > tableauNbConfiguration[reponseNiveau]);			
			
		}	
		
		reponseNiveau = reponseNiveau == 0 ? (int)((Math.random()*10)%3)+1: reponseNiveau;
		reponseConfiguration = reponseConfiguration == 0 ? (int)((Math.random()*10)%tableauNbConfiguration[reponseNiveau])+1: reponseConfiguration;

		
		ParkingFactory pf = new ParkingFactory(reponseNiveau, reponseConfiguration);
		Player player = new Player(pseudo);
		player.play(pf.getParking());
    }
}
