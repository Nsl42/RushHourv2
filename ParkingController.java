/** ParkingController, the class which performs all the checks on the vehicule's moves **/

public class ParkingController{ 

   Parking park;

   public ParkingController(Parking parking)
   {
      this.park = parking;
   }

   /** chkdist : The Check Distance method
    * @param String move The move to check
    * @return boolean ret True if the move is valid, false if not.
    **/

   public boolean chkdist(String move)
   {
      boolean ret = true;
      Vehicule v = this.park.vehicules.get(move.charAt(0));
      switch (v.getDirection())
      {
	 case Constants.HORIZONTAL:
	    switch (move.charAt(1))
	    {
	       case Constants.LEFT:
		  int newx = v.getXPosition - Character.getNumericValue(move.charAt(2))*Constants.SQUARE;
		  if(newx < 0)
		     ret = false;
		  break;
	       case Constants.RIGHT:
		  int  bx= v.getXPosition()+(Constants.SQUARE * v.getSize());
		  int newbx = bx + Character.getNumericValue(move.charAt(2))*Constants.SQUARE;
		  if(newbx > Constants.SIZE)
		     ret = false;
		  if(move.charAt(0) == "X")
		     ret = true;
		  break;
	    } 
	    break;
	 case Constants.VERTICAL:
	    switch (move.charAt(1))
	    {
	       case Constants.UP:
		  int newy = v.getYPosition() - Character.getNumericValue(move.charAt(2)*Constants.SQUARE);
		  if(newy <0)
		     ret = false;
		  break;
	       case Constants.DOWN:
		  int  cy= v.getYPosition()+(Constants.SQUARE * v.getSize());
		  int newcy = cy + Character.getNumericValue(move.charAt(2)*Constants.SQUARE);
		  if(newcy > Constants.SIZE)
		     ret = false;
		  break;
	    } 
	    break;
      }
      return ret;
   }

   /** chkdirec ; the Check Direction method
    * @param String move The move to check
    * @return boolean ret True if the move is valid, false if not.
    **/
   public boolean chkDirec(String move)
   {
      boolean ret;
      Vehicule v = this.park.vehicules.get(move.charat(0));
      char order = move.charat(1);
      (v.getDirection() == order) ? ret = true : ret = false;
      return ret;
   }

   /**chkCollision ; the Check Collision (With other Cars) method
    * @param String move The move to check
    * @return boolean ret True if the move is valide, false if not.
    **/
   public boolean chkCollision(String move)
   {
      boolean ret = true;
      Vehicule v = this.park.vehicules.get(move.charat(0));
      Vehicule ghost = new Vehicule("g", (v.getDirection == Constants.HORIZONTAL)? v.getSize:1,(v.getDirection == Constants.VERTICAL)? v.getSize:1, v.getXPosition(), v.getYPosition(), "none");
      ghost.makeInvisible();
      Set keys = park.keyset();
      switch(v.getDirection())
      {
	 case Constants.HORIZONTAL:
	    switch(move.charAt(1))
	    {
	       case Constants.UP:
		  for(int i = 1; i < Character.getNumericValue(move.charAt(2)); i++)
		  {
		     ghost.moveVertical(-i)*Constants.SQUARE);
		     for(String key : keys)
		     {
			if(ghost.isOverlapping(park.get(key)))
			{
			   ret = false;
			}
		     }
		     ghost.moveVertical(i*Constants.SQUARE);
		  }
		  break;
	       case Constants.DOWN:
		  for(int i = 1; i < Character.getNumericValue(move.charAt(2)); i++)
		  {
		     ghost.moveVertical(i)*Constants.SQUARE);
		     for(String key : keys)
		     {
			if(ghost.isOverlapping(park.get(key)))
			{
			   ret = false;
			}
		     }
		     ghost.moveVertical(-i*Constants.SQUARE);
		  }
		  break;
	    }
	    break;
	 case Constants.HORIZONTAL:
	    switch(move.charAt(1))
	    {
	       case Constants.LEFT:
		  for(int i = 1; i < Character.getNumericValue(move.charAt(2)); i++)
		  {
		     ghost.moveHorizontal(-i)*Constants.SQUARE);
		     for(String key : keys)
		     {
			if(ghost.isOverlapping(park.get(key)))
			{
			   ret = false;
			}
		     }
		     ghost.moveHorizontal(i*Constants.SQUARE);
		  }
		  break;
	       case Constants.RIGHT:
		  for(int i = 1; i < Character.getNumericValue(move.charAt(2)); i++)
		  {
		     ghost.moveHorizontal(i)*Constants.SQUARE);
		     for(String key : keys)
		     {
			if(ghost.isOverlapping(park.get(key)))
			{
			   ret = false;
			}
		     }
		     ghost.moveHorizontal(-i*Constants.SQUARE);
		  }
		  break;
	    }
	    break;
      }
      return ret;
   }
}
