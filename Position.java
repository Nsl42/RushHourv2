public class Position{
   public   String car;
   public   String direction;
   public   int posX;
   public   int posY;

   public Position(String pos)
   {
      car = pos.substring(0,1);
      direction = pos.substring(1,2);
      posX = Integer.parseInt(pos.substring(2,3));
      posY = Integer.parseInt(pos.substring(3,4));
   }
}
