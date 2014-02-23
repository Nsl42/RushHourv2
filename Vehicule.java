
/**
*la classe vehicule permet la creation et la gestion de rectangle, imageant les vehicule sur un plateau de jeu RushHour
 */
public class Vehicule extends Rectangle
{
    	// instance variables - replace the example below with your own
    	private String direction;
   	 private int size;

    	/**
     	* constructeur de la class Vehicule
	* On entre le nom (X,A,B,C..)
	* La largeur et la longueur doivent etre de 1,2 ou 3 selon la taille du vehicule et sa direction.
	* Les positions x et y sont en nombre de case.
	* Les couleurs sont celles de Rectangle.
	*/
    public Vehicule(String nom,int width,int height,int posX,int posY,String color)
    { 
       super(nom,width*Constants.SQUARE,height*Constants.SQUARE,posX*Constants.SQUARE+Constants.BORDER,posY*Constants.SQUARE+Constants.BORDER,color);  //appel du constructeur de la classe mere pour initialiser les attributs herites

       if (width != 1){this.size = width;} //definition de la taille du vehicule (le chiffre le plus grand entre width et height)
       else{this.size = height;}

       if (width > height){this.direction = Constants.HORIZONTAL;} //definition de l'orientation du vehicule (HORIZONTAL ou VERTICAL)
       else{this.direction = Constants.VERTICAL;}
    }
	/** Getter method for Direction parameter
	 * @return char direc the Direction
	 **/
    public String getDirection()
    {
       return this.direction;
      }
	/** Getter method for size parameter
	 * @return char size the size
	 **/
    public int getSize()
    {
       return this.size;
      }
    	/**
	*appel la methode slowMove de la classe mere en fonction de la direction
	*pour up et left on doit passer la distance en negatif, car pour aller a gauche on diminu les x et pour monter on diminu les y
	*/
    public void move(String direction, int distance){
    	if(direction.equals("U")){
    		this.slowMoveVertical (-distance*Constants.SQUARE);
    	}else 	if(direction.equals("L")){
    			this.slowMoveHorizontal (-distance*Constants.SQUARE);
    		}else 	if (direction.equals("R")){
    				this.slowMoveHorizontal (distance*Constants.SQUARE);
    			}else{
    				this.slowMoveVertical (distance*Constants.SQUARE);
    			}
    }
}
