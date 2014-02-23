import java.util.*;

/**
 *Cette classe permet le gestion des vehicules et la coordination de leurs mouvements
 */
public class Parking
{
   Hashtable<String,Vehicule> vehicules; //tableau a deux dimention qui permet de garder une reference sur chaque vehicule
   private final ParkingController pController;


   /**
    *constructeur de parking
    *creation d'un objet Grid qui permet laffichage
    *initialisation de la hashtable vide
    */
   public Parking()
   { 
      new Grid ();
      vehicules = new Hashtable<String,Vehicule>();
      pController = new ParkingController(this);

   }


   /**
    *methode qui creer un nouveau vehicule, le rend visible, puis le stock dans la hashtable
    */
   public void addVehicule (String nom,int width,int height,int posX,int posY,String color){
      Vehicule v = new Vehicule(nom, width, height, posX, posY, color);
      v.makeVisible();
      vehicules.put (nom, v);
   }

   /**
    *permet de recuperer l'objet de type vehicule via son nom
    */
   public Vehicule getVehicule (String nom){
      return vehicules.get (nom);
   }


   /**
    *appel les methodes permettant le deplacement d'un vehicule
    */
   public void move(String deplacement){
      char [] tab = deplacement.toCharArray(); //division du string en un tableau de char
      //recuperation de l'objet vehicule par son nom puis appel de la methode move de cet objet
      //les parametres de ces methodes passe de char a String ou int via les methodes de la classe Character
      if(this.pController.chkDirec(deplacement))
      {
	 if(this.pController.chkDist(deplacement))
	 {
	    if(this.pController.chkCollision(deplacement))
	    {
	       Vehicule v = getVehicule(Character.toString(tab[0]));
	       int dist = Integer.parseInt(Character.toString(tab[2]));
	       v.move(Character.toString(tab [1]), dist);
	    }
	 }
      }
   }

}
