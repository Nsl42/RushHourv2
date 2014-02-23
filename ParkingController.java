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
      System.out.println(" || DIST || ");
      System.out.println(" Vehi : "+v.getText());
      System.out.println("   direc : "+v.getDirection()); 
      System.out.println("   move : "+move.substring(1,2));
      System.out.println("   dist : " +move.substring(2,3));
      System.out.println("   move.toString : " +move);
      switch (v.getDirection())
      {
	 case Constants.HORIZONTAL:
	    switch (move.substring(1,2))
	    {
	       case Constants.LEFT:
		  int newx = v.getXPosition() - Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  if(newx < 0)
		     ret = false;
		  break;
	       case Constants.RIGHT:
		  int  bx= v.getXPosition()+(Constants.SQUARE * v.getSize());
		  int newbx = bx + Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  if(newbx > Constants.SIZE)
		     ret = false;
		  if(move.substring(0,1).equals("X"))
		     ret = true;
		  break;
	    } 
	    break;
	 case Constants.VERTICAL:
	    switch (move.substring(1,2))
	    {
	       case Constants.UP:
		  int newy = v.getYPosition() - Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  if(newy <0)
		     ret = false;
		  break;
	       case Constants.DOWN:
		  int  cy= v.getYPosition()+(Constants.SQUARE * v.getSize());
		  int newcy = cy + Integer.parseInt(move.substring(2,3))*Constants.SQUARE;
		  if(newcy > Constants.SIZE)
		     ret = false;
		  break;
	    } 
	    break;
      }
      System.out.println("returns : " + Boolean.toString(ret));
      return ret;
   }

   /** chkdirec ; the Check Direction method
    * @param String move The move to check
    * @return boolean ret True if the move is valid, false if not.
    **/
   public boolean chkDirec(String move)
   {
      boolean ret;
      Vehicule v = this.park.vehicules.get(move.substring(0,1));
      String order = move.substring(1,2);
      //debug 
 	System.out.println(" || DIREC ||");
     System.out.println("vehi : " + v.getDirection());
     System.out.println("ordre : " + order);
     ret = (v.getDirection().equals(Constants.HORIZONTAL))?((order.equals(Constants.LEFT)||order.equals(Constants.RIGHT))?true:false):((order.equals(Constants.DOWN)||order.equals(Constants.UP))?true:false);
     System.out.println("returns : " +Boolean.toString(ret));
      return ret;
   }

   /**chkCollision ; the Check Collision (With other Cars) method
    * @param String move The move to check
    * @return boolean ret True if the move is valide, false if not.
    **/
   public boolean chkCollision(String move)
   {
      boolean ret = true;
      Vehicule v = this.park.vehicules.get(move.substring(0,1));
      System.out.println(move);
      Vehicule ghost = new Vehicule("g", (v.getDirection().equals(Constants.HORIZONTAL))? v.getSize():1,(v.getDirection().equals(Constants.VERTICAL))? v.getSize():1, v.getXPosition()/Constants.SQUARE, v.getYPosition()/Constants.SQUARE, "black");

      //ghost.makeInvisible();
      //debug
      System.out.println(" || COLLISIONS || ");
      System.out.println(" Vehi : "+v.getText());
      System.out.println("   direc : "+v.getDirection()); 
      System.out.println("   move : "+move.substring(1,2));
      Set<String> keys = park.vehicules.keySet();
      switch(v.getDirection())
      {
	 case Constants.VERTICAL:
	    switch(move.substring(1,2))
	    {
	       case Constants.UP:
	       ///debug
     System.out.println("vehi : " + v.getDirection());
     System.out.println("ordre : " + move.substring(1,2));
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     ghost.moveVertical(-1*Constants.SQUARE);
		     for( String key : keys)
		     {
			System.out.println(Boolean.toString(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText()));
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			{
			   //debug
			   System.out.println("ghost : X " + ghost.getXPosition() + ", Y " + ghost.getYPosition());
			   System.out.println(park.vehicules.get(key).getText() + " : X " + park.vehicules.get(key).getXPosition() + ", Y " + park.vehicules.get(key).getYPosition());
			   ret = false;
			}
		     }
		  }
		  break;
	       case Constants.DOWN:
	       ///debug
     System.out.println("vehi : " + v.getDirection());
     System.out.println("ordre : " + move.substring(1,2));
		  for(int i = 1; i <= Integer.parseInt(move.substring(2,3)); i++)
		  {
		     ghost.moveVertical(Constants.SQUARE);
		     for(String key : keys)
		     {
			   //debug
			   System.out.println("ghost : X " + ghost.getXPosition() + ", Y " + ghost.getYPosition());
			   System.out.println(park.vehicules.get(key).getText() + " : X " + park.vehicules.get(key).getXPosition() + ", Y " + park.vehicules.get(key).getYPosition());

			System.out.println(Boolean.toString(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText()));
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			{
			   //debug
			   System.out.println("ghost : X " + ghost.getXPosition() + ", Y " + ghost.getYPosition());
			   System.out.println(park.vehicules.get(key).getText() + " : X " + park.vehicules.get(key).getXPosition() + ", Y " + park.vehicules.get(key).getYPosition());
			   ret = false;
			}
		     }
		  }
		  break;
	    }
	    break;
	 case Constants.HORIZONTAL:
	    switch(move.substring(1,2))
	    {
	       case Constants.LEFT:
	       ///debug
     System.out.println("vehi : " + v.getDirection());
     System.out.println("ordre : " + move.substring(1,2));
		  for(int i = 1; i < Integer.parseInt(move.substring(2,3)); i++)
		  {
		     ghost.moveHorizontal(-i*Constants.SQUARE);
		     for(String key : keys)
		     {
			System.out.println(Boolean.toString(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText()));
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			{
			   //debug
			   System.out.println("ghost : X " + ghost.getXPosition() + ", Y " + ghost.getYPosition());
			   System.out.println(park.vehicules.get(key).getText() + " : X " + park.vehicules.get(key).getXPosition() + ", Y " + park.vehicules.get(key).getYPosition());
			   ret = false;
			}
		     }
		     ghost.moveHorizontal(i*Constants.SQUARE);
		  }
		  break;
	       case Constants.RIGHT:
	       ///debug
     System.out.println("vehi : " + v.getDirection());
     System.out.println("ordre : " + move.substring(1,2));
		  for(int i = 1; i < Integer.parseInt(move.substring(2,3)); i++)
		  {
		     ghost.moveHorizontal(i*Constants.SQUARE);
		     for(String key : keys)
		     {
			System.out.println(Boolean.toString(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText()));
			if(ghost.isOverlapping(park.vehicules.get(key)) && v.getText() != park.vehicules.get(key).getText())
			{
			   //debug
			   System.out.println("ghost : X " + ghost.getXPosition() + ", Y " + ghost.getYPosition());
			   System.out.println(park.vehicules.get(key).getText() + " : X " + park.vehicules.get(key).getXPosition() + ", Y " + park.vehicules.get(key).getYPosition());
			   ret = false;
			}
		     }
		     ghost.moveHorizontal(-i*Constants.SQUARE);
		  }
		  break;
	    }
	    break;
      }
      //debug
      
      System.out.println("returns : " + Boolean.toString(ret));
      return ret;
   }
}
