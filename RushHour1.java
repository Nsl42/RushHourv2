/**
*la classe de test permet la verification du bon fonctionnement des autres classe en realisant un scenario precis
 */
public class RushHour1
{
    

    public static void main(String [] args)
    { 
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
	p.move("BD3");
    }
}

