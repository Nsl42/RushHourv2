import java.util.Scanner;

public class RushHour2 {
	private int score[] = {}; 
	
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
			System.out.println("1. Choisir un niveaux et un numéro de configuration");
			System.out.println("2. Choisir seulement le niveau");
			System.out.println("3. Niveau aléatoire");
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
		
		
		ParkingFactory parking = new ParkingFactory();
		Player player = new Player();

		reponseNiveau = reponseNiveau == 0 ? (int)((Math.random()*10)%3)+1: reponseNiveau;
		reponseConfiguration = reponseConfiguration == 0 ? (int)((Math.random()*10)%tableauNbConfiguration[reponseNiveau])+1: reponseConfiguration;
		player.play(reponseNiveau, reponseConfiguration, parking);
		
		
		
		//parkingFactory.chargerFichier(pseudo);
		
		
		
		
		/*
    	//creation du parking (plateau de jeu + espace de stockage des vehicules)
    	Parking p = new Parking ();

	//creation des vehicules
    	p.addVehicule("X", 2, 1, 1, 2, "red");
    	p.addVehicule("A", 2, 1, 1, 3, "green");
    	p.addVehicule("B", 1, 2, 1, 4, "orange");
    	p.addVehicule("C", 2, 1, 2, 5, "blue");
    	p.addVehicule("O", 1, 3, 3, 2, "yellow");
    	p.addVehicule("P", 1, 3, 5, 3, "purple");

    	//succession de deplacement de vehicule
    	p.move("PU3");
    	p.move("OU2");
    	p.move("CR2");
    	p.move("AR3");
    	p.move("OD3");
    	p.move("XR1");
    	p.move("BU4");
    	p.move("XL1");
    	p.move("OU3");
    	p.move("CL3");
    	p.move("AL3");
    	p.move("PD3");
    	p.move("OD3");
    	p.move("XR5");
    	*/
    }
}
