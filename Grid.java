/**
*Cette classe permet l'affichage du plateau de jeu de RushHour
*/
public class Grid
{
    
	/**
	*Constructeur de la classe Grid
	*initialise la grille du jeu
	*/
    public Grid()
    { 
    	new Rectangle (Constants.SIZE, Constants.SIZE, 0, 0, "dark_grey").makeVisible(); //création et affichage d'un rectangle de la taille de la fenetre (forme la bordure gris foncé)
    	new Rectangle (Constants.BORDER, Constants.SQUARE, 6*Constants.SQUARE+Constants.BORDER, 2*Constants.SQUARE+Constants.BORDER, "white").makeVisible(); //création et affichage d'un rectangle blanc pour imager la sortie du parking
    	new Rectangle (Constants.SIZE-2*Constants.BORDER, Constants.SIZE-2*Constants.BORDER, Constants.BORDER, Constants.BORDER, "white").makeVisible(); //création et affichage d'un rectangle blanc (forme le fond)
    	

	//boucle de création de rectangles gris imageant les rails sur lesquels se deplacent les voitures
	//x et y sont les positions, en pixel, des rectangles
	int x = Constants.BORDER;
    	for (int i = 0; i<6; i++){  //les i representent les colones
    		int y = Constants.BORDER;
    		x += Constants.BORDER;
    		for(int j = 0; j<6; j++){ //les j representent les lignes
    			y += Constants.BORDER;
    			new Rectangle (Constants.SQUARE-2*Constants.BORDER, Constants.SQUARE-2*Constants.BORDER, x, y, "grey").makeVisible(); //création du rectangles gris
    			y += Constants.SQUARE-Constants.BORDER;
    		}
    		x += Constants.SQUARE-Constants.BORDER;
    	}


    }
}
