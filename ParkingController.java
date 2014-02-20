
public class ParkingController {
	private static int tableauCollision[][] = {{0, 0, 0, 0, 0, 0}, 
											   {0, 0, 0, 0, 0, 0}, 
											   {0, 0, 0, 0, 0, 0}, 
											   {0, 0, 0, 0, 0, 0}, 
											   {0, 0, 0, 0, 0, 0}, 
											   {0, 0, 0, 0, 0, 0}}; 

	public static int getTableauCollision(int numCase)
	{
		return tableauCollision[0][numCase];
	}

}
