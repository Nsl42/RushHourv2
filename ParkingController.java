import java.util.Set;
/** ParkingController, the class which performs all the checks on the vehicule's moves **/
//TODO : Clean & Comment

public class ParkingController{ 

   Parking park;

   public ParkingController(Parking parking)
   {
      this.park = parking;
   }

   /** chkDist : The Check Distance method
    * @param String move The move to check
    * @return boolean ret True if the move is valid, false if not.
    **/

   public boolean chkDist(String move)
   {
      boolean ret = true;
      Vehicule v = this.park.vehicules.get(move.substring(0,1));

      //debug
      //System.out.println(" || DIST || ");
      //System.out.println(" Vehi : "+v.getText());
      //System.out.println("   direc : "+v.getDirection()); 
      //System.out.println("   move : "+move.substring(1,2));
      //System.out.println("   dist : " +move.substring(2,3));
      //System.out.println("   move.toString : " +move);

      //Switching on the direction of the vehicle
      switch (v.getDirection())
      {
	 case Constants.HORIZONTAL:
	    //Switching on the Direction of the movement
	    switch (move.substring(1,2))
	    {
	       case Constants.LEFT:
		  // New Coordinates
		  int newx = v.getXPosition() - Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  // if Out of Boundaries
		  if(newx < 0)
		     ret = false;
		  break;
	       case Constants.RIGHT:
		  //X coordinate of the B point of the rectangle
		  int  bx= v.getXPosition()+(Constants.SQUARE * v.getSize());
		  // New Coordinates
		  int newbx = bx + Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  // if Out of Boundaries
		  if(newbx > Constants.SIZE)
		     ret = false;
		  // if the Car is X => Go out of the field by the right means to win the game and is a valid movement
		  if(move.substring(0,1).equals("X"))
		     ret = true;
		  break;
	    } 
	    break;
	 case Constants.VERTICAL:
	    switch (move.substring(1,2))
	    {
	       case Constants.UP:
		  // New Coordinates
		  int newy = v.getYPosition() - Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  // if Out of Boundaries
		  if(newy <0)
		     ret = false;
		  break;
	       case Constants.DOWN:
		  //Y coordinate of the C point of the rectangle
		  int  cy = v.getYPosition()+(Constants.SQUARE * v.getSize());
		  // New Coordinates
		  int newcy = cy + Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  // if Out of Boundaries
		  if(newcy > Constants.SIZE)
		     ret = false;
		  break;
	    } 
	    break;
      }
      //debug
      //System.out.println("returns : " + Boolean.toString(ret));
      return ret;
   }

   /** chkdirec ; the Check Direction method
    * @param String move The move to check
    * @return boolean ret True if the move is valid, false if not.
    **/
   public boolean chkDirec(String move)
   {
      Vehicule v = this.park.vehicules.get(move.substring(0,1));
      String order = move.substring(1,2);
      //debug 
      //System.out.println(" || DIREC ||");
      //System.out.println("vehi : " + v.getDirection());
      //System.out.println("ordre : " + order);

      // Ret = true if (order = left||order == right) && v.getDirection == Horizontal) || ((order = up || order == down) && v.getDirection == Vertical)
      return (v.getDirection().equals(Constants.HORIZONTAL)) ?
	 ((order.equals(Constants.LEFT)||order.equals(Constants.RIGHT)) ? true : false)
	 : ((order.equals(Constants.DOWN)||order.equals(Constants.UP)) ? true : false);

      //debug
      //System.out.println("returns : " +Boolean.toString(ret));
   }

   /**chkCollision ; the Check Collision (With other Cars) method
    * @param String move The move to check
    * @return boolean ret True if the move is valide, false if not.
    **/
   public boolean chkCollision(String move)
   {
      boolean ret = true;
      Vehicule v = this.park.vehicules.get(move.substring(0,1));
      // Ghost car creation (for collision-testing purposes)
      Vehicule ghost = new Vehicule("g", 
	    (v.getDirection().equals(Constants.HORIZONTAL)) ? v.getSize() : 1,
	    (v.getDirection().equals(Constants.VERTICAL)) ? v.getSize() : 1,
	    v.getXPosition()/Constants.SQUARE, v.getYPosition()/Constants.SQUARE, "black");
      ghost.makeInvisible();
      //Keys preparation for HashTable-fetching
      Set<String> keys = park.vehicules.keySet();

      //debug
      //System.out.println(" || COLLISIONS || ");
      //System.out.println(" Vehi : "+v.getText());
      //System.out.println("   direc : "+v.getDirection()); 
      //System.out.println("   move : "+move.substring(1,2));

      switch(v.getDirection())
      {
	 case Constants.VERTICAL:
	    switch(move.substring(1,2))
	    {
	       case Constants.UP:
		  // For each tile,
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     // Moving the Ghost
		     ghost.moveVertical(-1*Constants.SQUARE);
		     // For each other car of the playground
		     for( String key : keys)
			// If Rectangle.isOverLapping(Vehicule);
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			   ret = false;
		  }
		  break;
	       case Constants.DOWN:
		  // For each tile,
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     // Moving the Ghost
		     ghost.moveVertical(Constants.SQUARE);
		     // For each other car of the playground
		     for(String key : keys)
			// If Rectangle.isOverLapping(Vehicule);
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			   ret = false;
		  }
		  break;
	    }
	    break;
	 case Constants.HORIZONTAL:
	    switch(move.substring(1,2))
	    {
	       case Constants.LEFT:
		  // For each tile,
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     // Moving the Ghost
		     ghost.moveHorizontal(-1*Constants.SQUARE);
		     // For each other car of the playground
		     for(String key : keys)
			// If Rectangle.isOverLapping(Vehicule);
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			   ret = false;
		  }
		  break;
	       case Constants.RIGHT:
		  // For each tile,
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     // Moving the Ghost
		     ghost.moveHorizontal(Constants.SQUARE);
		     // For each other car of the playground
		     for(String key : keys)
			// If Rectangle.isOverLapping(Vehicule);
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			   ret = false;
		  }
		  break;
	    }
	    break;
      }
      //debug
      //System.out.println("returns : " + Boolean.toString(ret));
      return ret;
   }
}
